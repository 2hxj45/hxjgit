package com.xz.library.admin.entity.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查出点赞和收藏个数 返回
 *
 * @author xz
 */
@Data
public class GiveLikeAndCollectNumberVO {
    /**
     * 收藏个数
     */
    @ApiModelProperty(value = "收藏个数")
    private Integer collectNumber;
    @ApiModelProperty(value = "点赞个数")
    private Integer giveLikeNumber;
}
