package com.xz.library.admin.bean;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xz.library.admin.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * java web token来管理
 * 用ThreadLocal可能会有内存泄漏的风险（比如抛异常没remove等，退而求其次每次都解析）
 *
 * @author xz
 */
@Service
@Slf4j
public class JwtManager implements TokenManager{
    /** 超时时间 单位/小时 默认有效期半天 */
    @Value("${jwt.timeout.hours:12}")
    private long jwtTimeoutDate;
    /** jwt秘钥 */
    private final static String SECRET = "!#$@(*(&#$@#xiezheng-niubi-class!@#$%";
    /** 请求头token名称 */
    private static final String TOKEN_HEADER = "token";
    /** 当前用户 用户名 对应的Claim key  value为String类型 */
    private static final String USERNAME = "userName";
    /** 当前用户 用户id 对应的Claim key value为Long类型 */
    private static final String USER_ID = "userId";
    /** 密码*/
    private static final String PASSWORD = "password";
    /** 加密规则 */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    /** ThreadLocal保存用户信息 */
    private static final ThreadLocal<LoginUser> LOGIN_USER_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public LoginUser getUser() {
        if (LOGIN_USER_THREAD_LOCAL.get() != null) {
            return LOGIN_USER_THREAD_LOCAL.get();
        }
        // 获取token信息
        String token = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getHeader(TOKEN_HEADER);
        return getUser(token);
    }

    @Override
    public LoginUser getUser(String token) {
        if (LOGIN_USER_THREAD_LOCAL.get() != null) {
            return LOGIN_USER_THREAD_LOCAL.get();
        }
        // JWT解析token
        DecodedJWT verify = null;
        try {
            verify = JWT.require(ALGORITHM).build().verify(token);
            Long userId = verify.getClaim(USER_ID).asLong();
            Validates.isTrue(userId == null, LoginFailureException::new);
            String username = verify.getClaim(USERNAME).asString();
            String password = verify.getClaim(PASSWORD).asString();
            Validates.isTrue(StringUtils.isBlank(username), LoginFailureException::new);
            Validates.isTrue(StringUtils.isBlank(password), LoginFailureException::new);
            //通过对象,可以拿出属性
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(userId);
            loginUser.setUserName(username);
            loginUser.setPassword(password);
            LOGIN_USER_THREAD_LOCAL.set(loginUser);
            return loginUser;
        } catch (JWTVerificationException e) {
            log.error("token校验异常 JWTVerificationException ", e);
            throw new LoginFailureException();
        } catch (Exception e) {
            log.error("token校验其他异常 Exception ", e);
            throw new LoginFailureException();
        }
    }

    @Override
    public void setUser(LoginUser user) {
        // 不需要本地缓存
    }

    @Override
    @Deprecated
    public void setUser(String token, LoginUser user) {
        // 不用set了
    }

    @Override
    public void removeUser(String token) {
        // 删除本地缓存
        LOGIN_USER_THREAD_LOCAL.remove();
    }

    @Override
    public String getToken(LoginUser user) {
        if (user.getUserName() == null || user.getUserId() == null) {
            throw new LoginFailureException("用户信息缺失，请联系系统管理员");
        }
        Date expiresDate = Date.from(LocalDateTime.now().plusHours(jwtTimeoutDate).atZone(ZoneId.systemDefault()).toInstant());
        String token = JWT.create()
                .withClaim(USERNAME, user.getUserName())
                .withClaim(USER_ID, user.getUserId())
                .withClaim(PASSWORD, user.getPassword())
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
}
