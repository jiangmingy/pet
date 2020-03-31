package com.ttm.pet.enums;

public enum ReturnStatusEnum {
    SUCCESS(100, "操作成功"),

    CLIENT_ERROR(400, "客户端错误"),

    SERVER_ERROR(500, "服务端错误"),

    BIZ_ERROR(700, "业务异常"),

    MUST_PARAM_ERROR(4001,"缺少必选项,%s"),

    ILLEGAL_PARAM_ERROR(4002,"参数非法"),

    YEAR_PARAM_ERROR(4003,"参数必须在一年"),

    PARAM_TRANS_ERROR(4997,"字段转换异常"),

    ALL_PARAMETER_ERROR(4998, "存在异常参数"),

    PARAMETER_ERROR(4999, "存在异常参数:%s"),

    UNKNOWN(5000, "未知异常"),

    MESSAGE_ERROR(5003, "消息处理异常"),

    RFC_ERROR(5002, "接口调用异常"),

    EXECUTESQL_ERROR(5004,"执行数据库操作异常"),

    JSON_ERROR(5005,"JSON解析异常"),

    DB_NO_DATA(202,"未查询到信息"),

    NO_USER(3001,"无此用户"),

    BLACK(3002,"黑名单用户"),

    NO_DATA(3006,"无数据"),

    SYS_ERROR(101,"系統异常");

    private int value;

    private String desc;

    ReturnStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static ReturnStatusEnum getEnumByCode(int code) {
        for (ReturnStatusEnum e : ReturnStatusEnum.values()) {
            if (e.getValue() == code) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "" + this.getValue();
    }
}
