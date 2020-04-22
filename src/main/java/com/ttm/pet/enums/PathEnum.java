package com.ttm.pet.enums;

public enum PathEnum {
    ALIYUN_HEAD_URL("https://oss.zglele.com/"),
    ADMIN_UPLOAD_PATH("pet_admin"),
    CUSTOMER_UPLOAD_PATH("pet_app");

    private String path;

    private PathEnum(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
