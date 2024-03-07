package com.xz.library.admin.entity.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查出子评论返回实体类
 *
 * @author xz
 */
@Data
public class ChildCommentVO {
    @ApiModelProperty(value = "评论用户id")
    private Long userId;
    @ApiModelProperty(value = "评论用户昵称")
    private String nickname;
    @ApiModelProperty(value = "被评论用户id")
    private Long beUserId;
    @ApiModelProperty(value = "被评论用户昵称")
    private String beNickname;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "评论创建时间")
    private String createTime;
}
