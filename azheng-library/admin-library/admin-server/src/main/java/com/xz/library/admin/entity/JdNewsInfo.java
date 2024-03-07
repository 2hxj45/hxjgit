package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 京东新闻存入数据库实体类
 *
 * @author xz
 */
@Data
@TableName("news_info")
public class JdNewsInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "标题", example = "收评：权重拉动沪指反弹涨0.17% 煤飞色舞行情再现")
    private String title;
    @ApiModelProperty(value = "时间", example = "2017-07-06 15:02")
    private String time;
    @ApiModelProperty(value = "来源", example = "新浪财经")
    private String src;
    @ApiModelProperty(value = "分类", example = "finance")
    private String category;
    @ApiModelProperty(value = "图片", example = "http://api.jisuapi.com/news/upload/201707/06160007_47862.jpg")
    private String pic;
    @ApiModelProperty(value = "内容", example = "7月6日消息，沪指早盘低开后保持震荡")
    private String content;
    @ApiModelProperty(value = "原文手机网址", example = "http://finance.sina.cn/stock/dpps/2017-07-06/detail-ifyhwefp0163259.d.html?vt=4&pos=108")
    private String url;
    @ApiModelProperty(value = "原文PC网址", example = "http://finance.sina.com.cn/stock/jsy/2017-07-06/doc-ifyhwefp0163259.shtml")
    private String weburl;
    @TableField("create_time")
    private String createTime;
    @ApiModelProperty(value = "频道", example = "头条")
    private String channel;

}
