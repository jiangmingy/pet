package com.ttm.pet.service.impl;

import com.ttm.pet.model.dto.Customer;
import com.ttm.pet.dao.CustomerMapper;
import com.ttm.pet.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
