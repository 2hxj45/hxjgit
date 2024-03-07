package com.xz.library.admin.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 参数用到新闻id和用户id通用实体类
 * 查点赞记录入参实体类
 * 和查出收藏记录共用
 *
 * @author xz
 */
@Data
public class QueryGiveLikeRecordParam {
    private Long userId;
    //    @NotBlank(message = "新闻id不能为null")只能用于String
    @ApiModelProperty(value = "新闻id", required = true, example = "12")
    @NotNull
    private Long newsId;
}
