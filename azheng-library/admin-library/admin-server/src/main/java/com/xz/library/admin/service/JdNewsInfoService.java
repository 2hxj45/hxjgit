package com.xz.library.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.library.admin.entity.JdNewsInfo;
import com.xz.library.admin.entity.jd.JdResult;
import com.xz.library.admin.entity.result.GiveLikeAndCollectNumberVO;

import java.util.List;

/**
 * @author xz
 */
public interface JdNewsInfoService {
    /**
     * 新闻信息存入数据库
     *
     * @param channel  频道
     * @param jdResult 开放接口返回
     * @return 存入了几条
     */
    public Integer jdNewsInfoInsert(JdResult jdResult, String channel);

    /**
     * 查出新闻
     *
     * @param channel
     * @return
     */
    public List<JdNewsInfo> queryNews(String channel);

    /**
     * 统计news_info表有几条数据
     *
     * @param info
     * @return
     */
    public Integer selectJdNewsInfoCount(JdNewsInfo info);

    /**
     * 查出新闻点赞和收藏个数
     *
     * @param newsId
     * @return
     */
    GiveLikeAndCollectNumberVO queryGiveLikeAndCollectNumber(Long newsId);
}
