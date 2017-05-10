package com.bm.insurance.cloud.sale.enums;

/**
 * 系统角色
 */
public enum RoleEnum {
    SUPER_ADMIN(1L, "SUPER_ADMIN", "系统最高权限"),
    ADMIN(2L, "ADMIN", "管理员"),
    MANAGER(3L, "MANAGER", "团队长"),
    ASSIGNER(4L, "ASSIGNER", "助理"),
    MEMBER(5L, "MEMBER", "组员");

    private long id;

    private String code;

    private String desc;

    RoleEnum(long id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 获取描述
     * @param id
     * @return
     */
    public static String getDesc(long id){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if(roleEnum.getId() == id){
                return roleEnum.getDesc();
            }
        }
        return null;
    }
}
