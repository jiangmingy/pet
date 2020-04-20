package com.ttm.pet.service.impl;

import com.ttm.pet.model.dto.VerificationCode;
import com.ttm.pet.dao.VerificationCodeMapper;
import com.ttm.pet.service.VerificationCodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 验证码表 服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-04-17
 */
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements VerificationCodeService {

}
