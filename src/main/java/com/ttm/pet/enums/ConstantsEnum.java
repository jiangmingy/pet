package com.ttm.pet.enums;

public enum ConstantsEnum {
    REGIEST_CODE(1),//注册验证码类型
    RESET_CODE(2);//重置密码类型

    private int code;

    private ConstantsEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
