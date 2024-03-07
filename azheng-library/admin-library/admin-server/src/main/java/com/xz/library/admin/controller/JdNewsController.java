package com.xz.library.admin.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xz.library.admin.bean.NoLogin;
import com.xz.library.admin.bean.TokenManager;
import com.xz.library.admin.entity.JdNewsInfo;
import com.xz.library.admin.entity.jd.JdResult;
import com.xz.library.admin.entity.param.GetNewsParam;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.CollectAndGiveLikeVo;
import com.xz.library.admin.entity.result.GiveLikeAndCollectNumberVO;
import com.xz.library.admin.service.CollectInfoService;
import com.xz.library.admin.service.GiveLikeInfoService;
import com.xz.library.admin.service.JdNewsInfoService;
import com.xz.library.admin.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xz
 */
@RestController
@Slf4j
@Api(tags = "京东云新闻相关")
public class JdNewsController {
    private final String JD_API_URL = "https://way.jd.com/jisuapi/get";
    private final String APP_KEY = "11268f6b6d0b6895d3d1e7441e3093e2";
    @Autowired
    TokenManager tokenManager;
    @Autowired
    JdNewsInfoService jdNewsInfoService;
    @Autowired
    CollectInfoService collectInfoService;
    @Autowired
    GiveLikeInfoService giveLikeInfoService;

    @NoLogin
    @RequestMapping(value = "setNews", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "调用接口拿到新闻信息存入数据库", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> setNews(@RequestBody GetNewsParam re) {
        Integer integer = 0;
        String channel = re.getChannel();
        HttpResponse response = HttpRequest.get(JD_API_URL + "?channel=" + re.getChannel() + "&num=" + re.getNum() + "&start=" + re.getStart() + "&appkey=" + APP_KEY).execute();
        String body = response.body();
        if (body != null) {
            JdResult jdResult = JSON.parseObject(body, JdResult.class);
            integer = jdNewsInfoService.jdNewsInfoInsert(jdResult, channel);
        } else {
            return Result.err("供应商接口返回为空");
        }
        return Result.ok(integer);
    }

    @NoLogin
    @RequestMapping(value = "getNews", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查出新闻数据", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<JdNewsInfo>> getNews(@RequestBody GetNewsParam re) {
        PageHelper.startPage(re.getPageIndex(), re.getPageSize());
        List<JdNewsInfo> jdNewsInfos = jdNewsInfoService.queryNews(re.getChannel());
        PageInfo<JdNewsInfo> jdNewsInfoPageInfo = new PageInfo<>(jdNewsInfos);
        return Result.ok(jdNewsInfoPageInfo);
    }


    /**
     * 查出新闻是否已经被点赞和收藏
     */
    @PostMapping("query/newsGiveLikeOrNo")
    @ApiOperation("新闻是否已经被点赞和收藏")
    public Result<CollectAndGiveLikeVo> newsGiveLikeOrNo(@RequestBody @Valid QueryGiveLikeRecordParam param) {
        CollectAndGiveLikeVo collectAndGiveLikeVo = new CollectAndGiveLikeVo();
        Long userId = tokenManager.getUser().getUserId();
        param.setUserId(userId);
        Boolean aBoolean = collectInfoService.whetherCollect(param);
        Boolean aBoolean1 = giveLikeInfoService.whetherGiveLike(param);
        collectAndGiveLikeVo.setCollectInfo(aBoolean);
        collectAndGiveLikeVo.setGiveLikeInfo(aBoolean1);
        return Result.ok(collectAndGiveLikeVo);
    }


    @PostMapping("query/newsGiveLikeAndCollect")
    @NoLogin
    @ApiOperation("新闻被点赞和收藏的次数(未登录也可以使用)")
    public Result<GiveLikeAndCollectNumberVO> newsGiveLikeAndCollect(@RequestBody QueryGiveLikeRecordParam newsId) {
        GiveLikeAndCollectNumberVO giveLikeAndCollectNumberVO = jdNewsInfoService.queryGiveLikeAndCollectNumber(newsId.getNewsId());
        return Result.ok(giveLikeAndCollectNumberVO);
    }
}
