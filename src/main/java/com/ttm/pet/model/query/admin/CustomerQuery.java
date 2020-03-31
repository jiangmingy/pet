package com.ttm.pet.model.query.admin;

import com.ttm.pet.model.query.PageBeanQuery;
import io.swagger.annotations.ApiModelProperty;

public class CustomerQuery extends PageBeanQuery {

    @ApiModelProperty(value = "用户名", required = false)
    private String customerName;

    @ApiModelProperty(value = "手机号", required = false)
    private String mobile;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
