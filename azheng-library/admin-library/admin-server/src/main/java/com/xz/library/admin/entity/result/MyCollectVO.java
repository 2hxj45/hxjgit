package com.xz.library.admin.entity.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查出我的收藏返回实体类
 *
 * @author xz
 */
@Data
public class MyCollectVO {
    @ApiModelProperty(value = "新闻id")
    private Long newsId;
    @ApiModelProperty(value = "收藏时间")
    private String createTime;
    @ApiModelProperty(value = "新闻标题", example = "收评：北向资金净买入57.31亿元 沪股通净买入15.41亿元")
    private String title;
}
