package com.xz.library.admin.entity.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新闻是否已经被点赞和收藏返回前端实体类
 *
 * @author xz
 */
@Data
public class CollectAndGiveLikeVo {
    @ApiModelProperty(value = "是否点赞,true:已点,false:没点")
    private Boolean giveLikeInfo;

    @ApiModelProperty(value = "是否收藏,true:已收藏,false:未收藏")
    private Boolean collectInfo;
}
