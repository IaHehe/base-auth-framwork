package com.bm.insurance.cloud.sale.enums;


public enum StatusEnum {
    EABLE(1, "应用"),
    DISABLE(2, "禁用");

    private int code;

    private String desc;

    StatusEnum(int code, String desc) {
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
