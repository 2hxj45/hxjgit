package com.xz.library.admin.entity.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xz
 */
@Data
public class UserCommentVO {
    @ApiModelProperty(value = "新闻id", example = "12")
    private Long newsId;
    @ApiModelProperty(value = "评论id", example = "12")
    private Long commentId;
    @ApiModelProperty(value = "评论人id", example = "12")
    private Long userId;
    @ApiModelProperty(value = "评论人昵称", example = "12")
    private String nickname;
    @ApiModelProperty(value = "评论内容", example = "12")
    private String content;
    @ApiModelProperty(value = "评论时间", example = "2021-05-01 15:00:00")
    private String createTime;
    @ApiModelProperty(value = "点赞数", example = "2")
    private Long likenumInt;
    @ApiModelProperty(value = "评论下有几条子评论", example = "2")
    private Long childComments;
    @ApiModelProperty(value = "该用户是否点赞,为0则为没点赞,为1才为点赞", example = "1")
    private Integer whetherGiveLike;

}
