package com.xz.library.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户注册信息
 * 实体类
 *
 * @author xz
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_account")
public class RegisterInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(example = "用户名:账号", required = true, value = "解释:用户名就是手机号")
    @NotBlank(message = "账号不能为空")
    @Length(min = 6, max = 11, message = "请设置6-11位的账号")
    @TableField("user_name")
    private String userName;
    @ApiModelProperty(value = "密码", required = true, example = "123java")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 15, message = "密码长度在6字符-15字符之间")
    @TableField("password")
    private String password;
    @ApiModelProperty(value = "昵称", required = true, example = "wdnmd")
    @NotBlank(message = "昵称不能为空")
    @Length(min = 2, max = 15, message = "昵称长度在2字符-15字符之间")
    @TableField("nickname")
    private String nickname;
//    @ApiModelProperty(value = "手机号", required = true, example = "13607353698")
//    @NotBlank(message = "手机号不能为空")
//    @Length(min = 11, max = 20, message = "手机号长度在11位-20位之间")
//    @TableField("cellPhone_Number")
//    private String cellPhoneNumber;
    @TableField("create_time")
    private Date createTime;
    @ApiModelProperty(value = "性别",name = "0:女;1:男",example = "")
    @TableField("gender")
    private Integer gender;
    @ApiModelProperty(value = "年龄", example = "18")
    @TableField("age")
    private Integer age;

}
