package com.ttm.pet.enums;

public enum PathEnum {
    ALIYUN_HEAD_URL("https://pet-new.oss-cn-shanghai.aliyuncs.com/"),//回头需要替换为三级域名
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
