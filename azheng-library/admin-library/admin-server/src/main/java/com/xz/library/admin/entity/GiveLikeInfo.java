package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 点赞信息表实体
 *
 * @author xz
 */
@Data
@TableName("give_like_info")
public class GiveLikeInfo {
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
    @ApiModelProperty(value = "新闻id,没有则为0",example = "12")
    private Long newsId;
    /**
     * 评论id，没有则为0
     */
    @TableField("comment_id")
    @ApiModelProperty(value = "评论id，没有则为0", example = "1")
    private Long commentId;

    @TableField("like_type")
    @ApiModelProperty(value = "点赞类型", example = "1")
    private Long likeType;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
