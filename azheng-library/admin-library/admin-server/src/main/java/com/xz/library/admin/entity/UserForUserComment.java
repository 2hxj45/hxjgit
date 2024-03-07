package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户评论用户表对应实体类
 * `id` bigint(20) NOT NULL AUTO_INCREMENT,
 * `user_id` bigint(20) NOT NULL COMMENT '评论人id',
 * `be_user_id` bigint(20) NOT NULL COMMENT '被评论人id',
 * `content` varchar(255) NOT NULL COMMENT '评论内容',
 * `comment_id` bigint(20) NOT NULL COMMENT '在哪条评论下',
 * `create_time` datetime DEFAULT NULL COMMENT '评论时间',
 *
 * @author xz
 */
@Data
@TableName("user_for_user_comment")
public class UserForUserComment {
    /**
     * 自增主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("be_user_id")
    @ApiModelProperty(value = "被评论人id", required = true, example = "2")
    private Long beUserId;

    @ApiModelProperty(value = "评论内容", required = true, example = "嚯嚯哈一")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "在哪条评论下", required = true, example = "1")
    @TableField("comment_id")
    private Long commentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("news_id")
    private Long newsId;

}
