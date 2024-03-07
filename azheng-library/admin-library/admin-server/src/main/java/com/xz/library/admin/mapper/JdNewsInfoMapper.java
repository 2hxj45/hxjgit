package com.xz.library.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.library.admin.entity.JdNewsInfo;
import com.xz.library.admin.entity.RegisterInfo;
import com.xz.library.admin.entity.jd.JdNewsDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xz
 */

public interface JdNewsInfoMapper extends BaseMapper<JdNewsInfo> {
    /**
     * 新闻信息存入数据库
     *
     * @param list
     * @return
     */
    int jdNewsInfoInsert(@Param("list") List<JdNewsDetails> list);

    /**
     * 测试xml查出东西
     *
     * @return
     */
    List<RegisterInfo> query();

    /**
     * 查出新闻信息
     *
     * @return
     */
    List<JdNewsInfo> queryJdNewsDetails(String channel);

    /**
     * 查出news_info表的所有新闻标题
     *
     * @return
     */
    List<String> getNewsTitle();

    /**
     * 查出新闻点赞个数
     *
     * @param newsId
     * @return
     */
    Integer queryGiveLikeNumber(Long newsId);

    /**
     * 查出新闻收藏个数
     *
     * @param newsId
     * @return
     */
    Integer queryCollectNumber(Long newsId);
}
