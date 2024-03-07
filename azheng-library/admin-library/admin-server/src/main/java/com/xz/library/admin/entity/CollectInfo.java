package com.xz.library.admin.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 收藏信息表对应实体
 *
 * @author xz
 */
@Data
@TableName("collect_info")
public class CollectInfo {
    /**
     * 自增主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 新闻id
     */
    @TableField("news_id")
    @ApiModelProperty(value = "新闻id", required = true,example = "12")
    private Long newsId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

}
