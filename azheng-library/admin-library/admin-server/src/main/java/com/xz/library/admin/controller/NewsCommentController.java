package com.xz.library.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.library.admin.bean.NoLogin;
import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.JdNewsInfo;
import com.xz.library.admin.entity.UserComment;
import com.xz.library.admin.entity.UserForUserComment;
import com.xz.library.admin.entity.param.ChildCommentParam;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.ChildCommentVO;
import com.xz.library.admin.entity.result.UserCommentVO;
import com.xz.library.admin.mapper.JdNewsInfoMapper;
import com.xz.library.admin.service.UserCommentService;
import com.xz.library.admin.service.UserForUserCommentService;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 用户评论相关
 *
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "用户评论相关")
public class NewsCommentController {
    @Autowired
    TokenManager tokenManager;
    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    UserForUserCommentService userForUserCommentService;
    @Autowired
    JdNewsInfoMapper jdNewsInfoMapper;

    @PostMapping("use/comment")
    @ApiOperation("用户评论新闻")
    public Result<Boolean> userComment(@Valid @RequestBody UserComment param) {
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        param.setCreateTime(new Date());
        Boolean aBoolean = userCommentService.saveUserComment(param);
        return Result.ok(aBoolean);
    }

    @PostMapping("query/comment")
    @ApiOperation("查出新闻的评论")
    public Result<List<UserCommentVO>> queryUserComment(@RequestBody QueryGiveLikeRecordParam param) {
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        List<UserCommentVO> userCommentVOS = userCommentService.queryUserComment(param);
        return Result.ok(userCommentVOS);
    }

    @PostMapping("comment/insertComment")
    @ApiOperation("用户评论用户")
    public Result<Boolean> insertComment(@RequestBody UserForUserComment param) {
        //当前登录人id,也就是评论人id
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        param.setCreateTime(new Date());
        Boolean aBoolean = userForUserCommentService.insertComment(param);
        return Result.ok(aBoolean);
    }

    /**
     * 查出评论的子评论
     * 评论展开
     */

/**
 * AccessKey ID
 * LTAI5tJGhAKpR9xbn7zbPXBy
 * AccessKey Secret
 * qTRUCUFAwKusjc22iEhTl7LXcu9OXX
 */

    /**
     * 根据newsId查出新闻
     */
    @PostMapping("query/newsById")
    @ApiOperation("查出单个新闻内容")
    public Result<JdNewsInfo> queryNewsById(@RequestBody QueryGiveLikeRecordParam param) {
        JdNewsInfo jdNewsInfo = new JdNewsInfo();
        jdNewsInfo.setId(param.getNewsId());
        QueryWrapper<JdNewsInfo> jdNewsInfoQueryWrapper = new QueryWrapper<>();
        jdNewsInfoQueryWrapper.setEntity(jdNewsInfo);
        JdNewsInfo jdNewsInfo1 = jdNewsInfoMapper.selectOne(jdNewsInfoQueryWrapper);
        return Result.ok(jdNewsInfo1);
    }

    @PostMapping("query/userForUserComment")
    @NoLogin
    @ApiOperation("查出子评论(未登录也可以使用)")
    public Result<List<ChildCommentVO>> userForUserComment(@RequestBody ChildCommentParam param) {
        List<ChildCommentVO> childCommentVOS = userCommentService.queryUserForUserComment(param);
        return Result.ok(childCommentVOS);
    }

}
