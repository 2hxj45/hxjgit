package com.xz.library.admin.controller;

import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.CollectInfo;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.CollectAndGiveLikeVo;
import com.xz.library.admin.service.CollectInfoService;
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
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "收藏相关")
public class CollectInfoController {
    @Autowired
    private CollectInfoService collectInfoService;
    @Autowired
    TokenManager tokenManager;

    @PostMapping("/collectInfo/insert")
    @ApiOperation("用户增加收藏")
    public Result<Boolean> insertCollect(@RequestBody CollectInfo collectInfo) {
        QueryGiveLikeRecordParam queryGiveLikeRecordParam = new QueryGiveLikeRecordParam();
        Long userId = tokenManager.getUser().getUserId();
        collectInfo.setUserId(userId);
        collectInfo.setCreateTime(new Date());
        queryGiveLikeRecordParam.setUserId(userId);
        queryGiveLikeRecordParam.setNewsId(collectInfo.getNewsId());
        Boolean aBoolean1 = collectInfoService.whetherCollect(queryGiveLikeRecordParam);
        if (aBoolean1) {
            return Result.err("请勿重复收藏");
        }
        Boolean aBoolean = collectInfoService.insertCollect(collectInfo);
        return Result.ok(aBoolean);
    }

    @PostMapping("/collectInfo/delete")
    @ApiOperation("取消收藏")
    public Result<Boolean> deleteCollect(@RequestBody CollectInfo info) {
        Boolean aBoolean = false;
        QueryGiveLikeRecordParam queryGiveLikeRecordParam = new QueryGiveLikeRecordParam();
        Long userId = tokenManager.getUser().getUserId();
        info.setUserId(userId);
        Long newsId = info.getNewsId();
        queryGiveLikeRecordParam.setUserId(userId);
        queryGiveLikeRecordParam.setNewsId(newsId);
        Boolean aBoolean1 = collectInfoService.whetherCollect(queryGiveLikeRecordParam);
        if (aBoolean1) {
            aBoolean = collectInfoService.deleteCollect(info);
        } else {
            Result.err("还未收藏");
        }
        return Result.ok(aBoolean);
    }

}
