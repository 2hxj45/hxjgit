package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xz
 */
@Data
@TableName("user_comment")
public class UserComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("p_id")
    @ApiModelProperty(example = "0", required = true, value = "评论的是新闻则为默认0,用户评论的用户则为被评论的用户id")
    private Long pId;
    @TableField("news_id")
    @ApiModelProperty(example = "23", required = true, value = "评论的哪条新闻")
    @NotNull(message = "新闻id不能为空")
    private Long newsId;
    @ApiModelProperty(example = "毕设不简单", required = true, value = "评论内容")
    @NotNull(message = "评论内容不能为空")
    private String content;
    @TableField("create_time")
    private Date createTime;
}
