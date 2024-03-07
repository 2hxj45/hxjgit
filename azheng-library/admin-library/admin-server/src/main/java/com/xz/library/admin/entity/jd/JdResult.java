package com.xz.library.admin.entity.jd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 京东公共返回参数
 *
 * @author xz
 */
@Data
public class JdResult {
    @ApiModelProperty(value = "更多返回参数示例值请参看“错误参照码", example = "10000")
    private String code;
    @ApiModelProperty(value = "false：不扣费 true：扣费", example = "false 或 true")
    private Boolean charge;
    @ApiModelProperty(value = "更多返回参数示例值请参看“错误参照码”", example = "查询成功")
    private String msg;
    @ApiModelProperty(value = "", example = "数据查询结果")
    private List<JdNewsResult> result;
}
