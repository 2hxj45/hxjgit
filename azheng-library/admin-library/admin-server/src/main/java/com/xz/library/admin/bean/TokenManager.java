package com.xz.library.admin.bean;

import com.xz.library.admin.entity.LoginUser;

/**
 * @author xz
 */
public interface TokenManager {
    /**
     * 从request当中获取请求头中的token反解析出user
     *
     * @return 登录用户，没有返回null
     */
    LoginUser getUser();

    /**
     * 根据token获取登录用户
     *
     * @param token token
     * @return 返回登录用户，没有返回null
     */
    LoginUser getUser(String token);

    /**
     * 设置用户缓存信息
     *
     * @param user 用户信息
     */
    void setUser(LoginUser user);

    /**
     * 返回token
     *
     * @param token
     * @param user
     * @return
     */
    void setUser(String token, LoginUser user);

    /**
     * 删除用户
     *
     * @param token token
     */
    void removeUser(String token);

    /**
     * JWT使用
     * 根据loginUser获取token
     *
     * @param user 用户信息
     * @return
     */
    String getToken(LoginUser user);

}
