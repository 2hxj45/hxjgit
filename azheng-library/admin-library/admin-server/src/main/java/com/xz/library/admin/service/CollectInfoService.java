package com.xz.library.admin.service;

import com.xz.library.admin.entity.CollectInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.MyCollectVO;

import java.util.List;

/**
 * 收藏信息表对应服务类
 *
 * @author xz
 */
public interface CollectInfoService {
    /**
     * 添加收藏
     *
     * @param collectInfo
     * @return
     */
    Boolean insertCollect(CollectInfo collectInfo);

    /**
     * 查出我的收藏
     *
     * @param userId
     * @return
     */
    List<MyCollectVO> queryMyCollect(Long userId);

    /**
     * 删除收藏
     *
     * @param info
     * @return
     */
    Boolean deleteCollect(CollectInfo info);

    /**
     * 是否有该用户对某新闻的收藏记录
     * 根据userId和newsId查出该用户是否对这个新闻进行收藏
     *
     * @param param
     * @return
     */
    Boolean whetherCollect(QueryGiveLikeRecordParam param);
}
