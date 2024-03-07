package com.xz.library.admin.controller;

import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.GiveLikeInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.service.GiveLikeInfoService;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 点赞相关
 *
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "点赞相关")
public class GiveLikeInfoController {
    @Autowired
    TokenManager tokenManager;
    @Autowired
    GiveLikeInfoService giveLikeInfoService;

    @PostMapping("giveLike/insertInfo")
    @ApiOperation("用户点赞")
    public Result<Boolean> insertGiveLikeInfo(@RequestBody GiveLikeInfo param) {
        Boolean aBoolean = false;
        QueryGiveLikeRecordParam queryGiveLikeRecordParam = new QueryGiveLikeRecordParam();
        //当前登录人id
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        param.setCreateTime(new Date());
        queryGiveLikeRecordParam.setNewsId(param.getNewsId());
        queryGiveLikeRecordParam.setUserId(userId);
        Boolean whetherGiveLike = giveLikeInfoService.whetherGiveLike(queryGiveLikeRecordParam);
        if (whetherGiveLike){
            return Result.err("请勿重复点赞");
        }
        aBoolean = giveLikeInfoService.insertGiveLikeInfo(param);
        return Result.ok(aBoolean);
    }

    @PostMapping("giveLike/delete")
    @ApiOperation("用户取消点赞")
    public Result<Boolean> deleteGiveLikeInfo(@RequestBody GiveLikeInfo param) {
        //当前登录人id
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        Boolean aBoolean = giveLikeInfoService.deleteGiveLikeInfo(param);
        return Result.ok(aBoolean);
    }


}
