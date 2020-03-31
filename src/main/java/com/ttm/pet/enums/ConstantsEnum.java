package com.ttm.pet.enums;

public enum ConstantsEnum {
    TEST(1),
    TEST2(1);

    private int code;

    private ConstantsEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
