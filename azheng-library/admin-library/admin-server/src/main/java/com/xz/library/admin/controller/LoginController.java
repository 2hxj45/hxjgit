package com.xz.library.admin.controller;

import com.xz.library.admin.bean.NoLogin;
import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.bean.UserContext;
import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.UserInfo;
import com.xz.library.admin.entity.result.LoginResult;
import com.xz.library.admin.service.UserInfoService;
import com.xz.library.admin.service.UserService;
import com.xz.library.admin.utils.GenerateUtil;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * 登录和注册
 *
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "登录")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TokenManager tokenManager;

    /**
     * 用户名+密码
     * 手机号+密码+验证码注册
     * 注册
     */
    @NoLogin
    @PostMapping("common/verifyRegister")
    @ApiOperation("注册")
    public Result verifyRegister(@Valid @RequestBody RegisterInfo info) {
        Date date = new Date();
        info.setCreateTime(date);
        String userPassword = GenerateUtil.getUserPassword(info.getUserName() + info.getPassword());
        info.setPassword(userPassword);
        boolean b = userService.saveUser(info);
        return Result.ok(b);
    }

    @PostMapping("/login")
    @NoLogin
    @CrossOrigin
    @ApiOperation("登录")
    public Result<LoginResult> login(@Valid @RequestBody UserInfo info) {
        log.info("登录用户名和密码{}", info);
        String phoneNumber = info.getUserName();
        String password = info.getPassword();
        //用户输入密码加密
        String userPassword = GenerateUtil.getUserPassword(phoneNumber + password);
        //查出登录者全部信息,包括id  对比数据库储存加密后的密码和账号
        LoginUser loginUser = userService.getLoginUser(phoneNumber, userPassword);
        Objects.requireNonNull(loginUser, "用户名密码不正确");
        String token = tokenManager.getToken(loginUser);
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(token);
        return Result.ok(loginResult);
    }

    @GetMapping("/")
    @NoLogin
    @CrossOrigin
    @ApiOperation("hello")
    public Result<String> hello() {
        return Result.ok("hello world");
    }

    @GetMapping("logout")
    @CrossOrigin
    @ApiOperation("退出登录")
    public Result logout() {
        String token = UserContext.getToken();
        tokenManager.removeUser(token);
        return Result.ok();
    }
}
