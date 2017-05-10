package com.bm.insurance.cloud.sale.enums;


/**
 * 状态码enum类
 */
public enum StatusCodeEnum {
    SUCCESS(0, "成功"),
    BAD_REQUEST(400, "Bad Request!"),
    NOTAUTHORIZATION(401, "NotAuthorization"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    SERVER_ERROR(500, "Internal Server Error"),

    SERVER_RUN_EX(1000, "[服务器]运行时异常"),
    SERVER_NULL_EX(1001, "[服务器]空值异常"),
    PARSE_EX(1002, "[服务器]数据类型转换异常"),
    IO_EX(1003, "[服务器]IO异常"),
    UNKNOW_EX(1004, "[服务器]未知方法异常"),
    ARRAY_EX(1005, "[服务器]数组越界异常"),
    NET_EX(1006, "[服务器]网络异常"),

    NO_USER(1010, "用户未注册"),
    EXISTS_USER(1011, "用户已注册"),
    BAD_PASSWORD(1012, "密码不能为空"),
    NEED_ARGS(2010, "缺少参数或值为空"),

    NON_ARGS(2020, "参数不合法"),
    NO_AUTH(2021, "无操作权限"),
    NO_GROUP(2022,"用户没有被分配团队，无权限");

    private int code;

    private String desc;

    StatusCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
