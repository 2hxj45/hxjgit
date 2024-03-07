package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
public class UserInfo {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(example="用户名",required = true,value = "解释:用户名称")//swagger注解
    @TableField("user_name")
    /**
     * 用户名 数据库字段:user_name
     */
    private String userName;
    @TableField("user_password")
    /**
     * @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
     */
    private String password;
//    @TableField("cell_phone_number")
//    private String cellPhoneNumber;
}
