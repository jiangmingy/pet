package com.ttm.pet.model.pojo;

import com.ttm.pet.enums.ReturnStatusEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class BaseResult implements Serializable {

    private int code = ReturnStatusEnum.SUCCESS.getValue();

    private String msg = ReturnStatusEnum.SUCCESS.getDesc();

    public BaseResult() {
        super();
    }

    public BaseResult(int status, String message) {
        super();
        this.code = status;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}

