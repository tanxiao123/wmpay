package com.wmpay.common;

public enum  AdminTypeEnum {

    // 管理员级别登陆
    WM_SYSTEM_ADMIN(1, "admin"),
    // 代理级别登陆
    WM_ADDITION_ADMIN(2,"addition");

    public Integer code;

    public String name;


    AdminTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
