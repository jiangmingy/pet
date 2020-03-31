package com.ttm.pet.enums;

public enum PathEnum {
    FAILE_PATH("/.../test..../");

    private String path;

    private PathEnum(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
