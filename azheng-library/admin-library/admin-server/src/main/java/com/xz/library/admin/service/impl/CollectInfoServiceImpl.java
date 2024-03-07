package com.xz.library.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.library.admin.entity.CollectInfo;
import com.xz.library.admin.entity.param.QueryGiveLikeRecordParam;
import com.xz.library.admin.entity.result.MyCollectVO;
import com.xz.library.admin.mapper.CollectInfoMapper;
import com.xz.library.admin.service.CollectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xz
 */
@Service
public class CollectInfoServiceImpl implements CollectInfoService {
    @Autowired
    private CollectInfoMapper collectInfoMapper;


    @Override
    public Boolean insertCollect(CollectInfo collectInfo) {
        int insert = collectInfoMapper.insert(collectInfo);
        return insert > 0;
    }

    @Override
    public List<MyCollectVO> queryMyCollect(Long userId) {
        List<MyCollectVO> myCollectVOS = collectInfoMapper.queryMyCollect(userId);
        return myCollectVOS;
    }

    @Override
    public Boolean deleteCollect(CollectInfo info) {
        QueryWrapper<CollectInfo> collectInfoQueryWrapper = new QueryWrapper<>(info);
        int delete = collectInfoMapper.delete(collectInfoQueryWrapper);
        return delete > 0;
    }

    @Override
    public Boolean whetherCollect(QueryGiveLikeRecordParam param) {
        Integer integer = collectInfoMapper.whetherCollect(param);
        return integer > 0;
    }
}
