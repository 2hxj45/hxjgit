package com.xz.library.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.library.admin.entity.JdNewsInfo;
import com.xz.library.admin.entity.jd.JdBusinessResult;
import com.xz.library.admin.entity.jd.JdNewsDetails;
import com.xz.library.admin.entity.jd.JdResult;
import com.xz.library.admin.entity.result.GiveLikeAndCollectNumberVO;
import com.xz.library.admin.mapper.JdNewsInfoMapper;
import com.xz.library.admin.service.JdNewsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xz
 */
@Service
@Slf4j
public class JdNewsInfoServiceImpl implements JdNewsInfoService {
    @Autowired
    private JdNewsInfoMapper jdNewsInfoMapper;

    @Override
    public Integer jdNewsInfoInsert(JdResult jdResult,String channel) {
        Integer i = 0;
        //JdBusinessResult
        try {
            //查出数据库储存的标题,标题不同才存入
            List<String> newsTitle = jdNewsInfoMapper.getNewsTitle();
            List<JdNewsDetails> jdNewsDetails2 = new ArrayList<>();
            jdResult.getResult().forEach(jdNewsResult -> {
                List<JdBusinessResult> result = jdNewsResult.getResult();
                result.forEach(jdBusinessResult -> {
                    List<JdNewsDetails> list = jdBusinessResult.getList();
                    list.forEach(jdNewsDetails -> {
                        String title = jdNewsDetails.getTitle();
                        jdNewsDetails.setCreateTime(new Date());
                        jdNewsDetails.setChannel(channel);
                        boolean contains = newsTitle.contains(title);
                        if (!contains) {
                            jdNewsDetails2.add(jdNewsDetails);
                        }
                    });
                });
            });
            if (jdNewsDetails2.size() > 0) {
                i = jdNewsInfoMapper.jdNewsInfoInsert(jdNewsDetails2);
            }
        } catch (Exception e) {
            log.info("存入新闻出错");
        }
        return i;
    }

    @Override
    public List<JdNewsInfo> queryNews(String channel) {
        List<JdNewsInfo> jdNewsInfos = jdNewsInfoMapper.queryJdNewsDetails(channel);
        return jdNewsInfos;
    }

    @Override
    public Integer selectJdNewsInfoCount(JdNewsInfo info) {
        QueryWrapper<JdNewsInfo> newsInfoQueryWrapper = new QueryWrapper<>(info);
        Integer integer = jdNewsInfoMapper.selectCount(newsInfoQueryWrapper);
        return integer;
    }

    @Override
    public GiveLikeAndCollectNumberVO queryGiveLikeAndCollectNumber(Long newsId) {
        GiveLikeAndCollectNumberVO giveLikeAndCollectNumberVO = new GiveLikeAndCollectNumberVO();
        Integer giveLikeNumber = jdNewsInfoMapper.queryGiveLikeNumber(newsId);
        Integer queryCollectNumber = jdNewsInfoMapper.queryCollectNumber(newsId);
        giveLikeAndCollectNumberVO.setGiveLikeNumber(giveLikeNumber);
        giveLikeAndCollectNumberVO.setCollectNumber(queryCollectNumber);
        return giveLikeAndCollectNumberVO;
    }
}
