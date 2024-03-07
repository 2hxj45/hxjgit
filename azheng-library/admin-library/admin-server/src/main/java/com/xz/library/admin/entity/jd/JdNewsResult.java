package com.xz.library.admin.entity.jd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 京东查出新闻业务返回参数
 *
 * @author xz
 */
@Data
public class JdNewsResult {
    @ApiModelProperty(value = "状态", example = "0")
    private Integer status;
    @ApiModelProperty(value = "信息", example = "ok")
    private String msg;
    @ApiModelProperty(value = "", example = "数据查询结果")
    private List<JdBusinessResult> result;
}
