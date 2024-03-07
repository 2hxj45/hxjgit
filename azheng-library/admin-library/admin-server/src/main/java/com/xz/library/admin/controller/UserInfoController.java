package com.xz.library.admin.controller;

import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.UserInfo;
import com.xz.library.admin.service.UserInfoService;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * swagger访问路径(http://localhost:8995/swagger-ui.html#/)
 *
 * @author xz
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "测试Swagger")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TokenManager tokenManager;

    @PostMapping("/queryUserInfo")
    @ApiOperation(value = "测试查出用户信息")
    public Result<List<UserInfo>> queryUserInfo() {
        log.info("进入方法");
        LoginUser user = tokenManager.getUser();
        String userName = user.getUserName();
        //测试token中拿值
        log.info("账号:{}", userName);
        log.info("密码:{}", user.getPassword());
        log.info("用户id:{}", user.getUserId());
        List<UserInfo> userInfos = userInfoService.queryUserInfo();
        log.info("返回List{}", userInfos);
        return Result.ok(userInfos);

    }

}
