package com.ttm.pet.controller.app;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ttm.pet.enums.ReturnStatusEnum;
import com.ttm.pet.model.dto.Customer;
import com.ttm.pet.model.pojo.DataResult;
import com.ttm.pet.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(value ="/app/customer",tags = "app-customer",description = "用户中心")
@RequestMapping("/app/customer")
public class CustomerApiController {
    private final static Logger logger = LoggerFactory.getLogger(CustomerApiController.class);

    @Autowired
    private CustomerService customerService;
    /**
     * 根据用户uuid查询用户
     * @param uuid
     * @return
     */
    @ApiOperation(httpMethod = "GET",value = "根据uuid查询用户",notes = "uuid示例：ce47b06d-91d5-433b-8d80-b32d173c3fbf")
    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public DataResult queryCustomerById(@ApiParam(value = "用户id" ,required=true ) @RequestParam("uuid") String uuid){
        try {
            EntityWrapper<Customer> customerEntityWrapper = new EntityWrapper<>();
            customerEntityWrapper.eq("uuid",uuid);
            Customer customer = customerService.selectOne(customerEntityWrapper);
            if(null == customer){
                return new DataResult(ReturnStatusEnum.CLIENT_ERROR.getValue(),"获取用户失败！",null);
            }else {
                return new DataResult(ReturnStatusEnum.SUCCESS.getValue(),"获取用户成功！",customer);
            }
        } catch (Exception e) {
            return new DataResult(ReturnStatusEnum.SERVER_ERROR.getValue(),"获取用户异常！",null);
        }
    }

}

