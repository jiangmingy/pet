package com.ttm.pet.model.query;


import io.swagger.annotations.ApiModelProperty;

public class PageBeanQuery {
    private static final Integer DEFAULT_LIMIT = 10;

    @ApiModelProperty(value = "页数", required = true)
    private Integer page;

    @ApiModelProperty(value = "每页个数", required = false)
    private Integer size = PageBeanQuery.DEFAULT_LIMIT;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
