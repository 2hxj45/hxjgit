package com.xz.library.admin.entity.param;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xz
 */
@Data
public class GetNewsParam {
    /**
     * 频道
     */
    @ApiModelProperty(value = "频道", required = true, example = "头条")
    private String channel;
    /**
     * 数量 默认10，最大40
     */
    @ApiModelProperty(value = "数量 默认10，最大40", example = "10")
    private String num;
    /**
     * 起始位置，默认0
     */
    @ApiModelProperty(value = "起始位置，默认0", example = "0")
    private String start;
    @ApiModelProperty(value = "第几页", required = true, example = "1")
    @NotNull(message = "分页条件不能为空")
    private Integer pageIndex;
    @ApiModelProperty(value = "一页几条", required = true, example = "10")
    @NotNull(message = "分页条件不能为空")
    private Integer pageSize;

}
