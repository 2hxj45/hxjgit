package com.xz.library.admin.utils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应结果")
public class Result<T> {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_ERROR = "100";
    public static final String CODE_LOGINFAILURE = "-1";

    private static Result ok = new Result();

    static {
        ok.setCode(CODE_SUCCESS);
    }

    @ApiModelProperty("响应码，'200'成功 '100'失败 '-1'登录失效")
    private String code;
    @ApiModelProperty("响应数据")
    private T data;
    @ApiModelProperty("响应信息")
    private String msg;

    public static Result ok() {
        return ok;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setCode(CODE_SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result err(String msg) {
        Result result = new Result();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        return result;
    }

    public static Result err(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
