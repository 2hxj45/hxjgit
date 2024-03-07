package com.xz.library.admin.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查出子评论入参
 *
 * @author xz
 */
@Data
public class ChildCommentParam {
    @ApiModelProperty(value = "评论id", required = true, example = "1")
    private Long commentId;
}
