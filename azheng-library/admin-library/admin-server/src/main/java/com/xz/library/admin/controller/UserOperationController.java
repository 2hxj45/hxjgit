package com.xz.library.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.LoginUser;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.UserInfo;
import com.xz.library.admin.entity.param.ChangPasswordParam;
import com.xz.library.admin.entity.result.MyCollectVO;
import com.xz.library.admin.service.CollectInfoService;
import com.xz.library.admin.service.UserInfoService;
import com.xz.library.admin.service.UserOperationService;
import com.xz.library.admin.utils.GenerateUtil;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户个人中心操作相关
 *
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "个人中心")
public class UserOperationController {
    @Autowired
    TokenManager tokenManager;
    @Autowired
    private UserOperationService userOperationService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    CollectInfoService collectInfoService;

    @PostMapping("common/perfectInformation")
    @ApiOperation("完善信息")
    public Result<Boolean> perfectInformation(@RequestBody RegisterInfo info) {
        info.setPassword(null);
        info.setUserName(null);
        Long userId = tokenManager.getUser().getUserId();
        info.setId(userId);
        Boolean aBoolean = userOperationService.perfectInformation(info);
        return Result.ok(aBoolean);
    }

    @PostMapping("common/changPassword")
    @ApiOperation("修改密码")
    public Result changPassword(@RequestBody ChangPasswordParam param) {
        // 用户输入的原密码
        String inputOriginalPwd = param.getOriginalPwd();
        String inputNewPwd = param.getNewPwd();
        String inputConfirmPwd = param.getConfirmPwd();
        if (!inputNewPwd.equals(inputConfirmPwd)) {
            return Result.err("新密码与确认密码不一致");
        }
        //当前登录人信息
        LoginUser user = tokenManager.getUser();
        Long userId = user.getUserId();
        String userName = user.getUserName();
        RegisterInfo userInfo = userInfoService.getUserInfo(userId);
        //数据库查出来的密码
        String password = userInfo.getPassword();
        //生成新密码
        String userPassword = GenerateUtil.getUserPassword(userName + inputNewPwd);
        //根据输入密码生成现密码
        String inputOriginalPassword = GenerateUtil.getUserPassword(userName + inputOriginalPwd);
        if (StringUtils.isBlank(inputOriginalPassword) || !inputOriginalPassword.equals(password)) {
            return Result.err("原密码输入错误");
        }
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setId(userId);
        registerInfo.setPassword(userPassword);
        Boolean aBoolean = userOperationService.perfectInformation(registerInfo);
        return Result.ok(aBoolean);
    }

    @PostMapping("common/getUserInfo")
    @ApiOperation("查出个人信息")
    public Result<RegisterInfo> getUserInfo() {
        Long userId = tokenManager.getUser().getUserId();
        RegisterInfo registerInfo = userInfoService.getUserInfo(userId);
        registerInfo.setId(null);
        return Result.ok(registerInfo);
    }

    @PostMapping("collect/queryMyCollect")
    @ApiOperation("查出我的收藏")
    public Result<List<MyCollectVO>> queryMyCollect() {
        Long userId = tokenManager.getUser().getUserId();
        List<MyCollectVO> myCollectVOS = collectInfoService.queryMyCollect(userId);
        return Result.ok(myCollectVOS);
    }
}
