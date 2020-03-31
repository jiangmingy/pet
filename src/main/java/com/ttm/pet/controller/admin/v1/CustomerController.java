package com.ttm.pet.controller.admin.v1;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ttm.pet.enums.ReturnStatusEnum;
import com.ttm.pet.model.dto.Customer;
import com.ttm.pet.model.pojo.ListDataResult;
import com.ttm.pet.model.query.admin.CustomerQuery;
import com.ttm.pet.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@RestController
@Api(value ="/admin/customer",tags = "admin-customer",description = "用户管理")
@RequestMapping("/admin/customer")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 查询用户列表
     * @param customerQuery
     * @return
     */
    @ApiOperation(httpMethod = "GET", tags = "admin-customer", value = "查询用户列表")
    @RequestMapping(value = "/users" ,method = RequestMethod.GET)
    private ListDataResult getUsers(CustomerQuery customerQuery){
        ListDataResult result = new ListDataResult();
        try {
            EntityWrapper<Customer> customer = new EntityWrapper<>();
            if (customerQuery.getCustomerName() != null && !"".equals(customerQuery.getCustomerName())){
                customer.like("name",customerQuery.getCustomerName());
            }
            if (customerQuery.getMobile() != null && !"".equals(customerQuery.getMobile())){
                customer.like("mobile",customerQuery.getMobile());
            }
            Page<Customer> page = new Page<>(customerQuery.getPage(),customerQuery.getSize());
            page = customerService.selectPage(page,customer);
            result.setData(page.getRecords());
            result.setTotal(page.getTotal());
            result.setCurrent(page.getCurrent());
        }catch (Exception e){
            result.setCode(ReturnStatusEnum.RFC_ERROR.getValue());
            result.setMsg(ReturnStatusEnum.RFC_ERROR.getDesc());
            logger.error("查询用户列表错误...");
            return result;
        }
        return result;
    }
}

