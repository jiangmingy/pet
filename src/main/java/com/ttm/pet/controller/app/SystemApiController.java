package com.ttm.pet.controller.app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ttm.pet.enums.ConstantsEnum;
import com.ttm.pet.enums.PathEnum;
import com.ttm.pet.enums.ReturnStatusEnum;
import com.ttm.pet.model.dto.Customer;
import com.ttm.pet.model.dto.VerificationCode;
import com.ttm.pet.model.pojo.BaseResult;
import com.ttm.pet.model.pojo.DataResult;
import com.ttm.pet.service.CustomerService;
import com.ttm.pet.service.VerificationCodeService;
import com.ttm.pet.util.FileUtils;
import com.ttm.pet.util.SMSUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/app/system",tags = "app-system",description = "通用接口")
@RequestMapping("/app/system")
public class SystemApiController {
    private final static Logger logger = LoggerFactory.getLogger(SystemApiController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @ApiOperation( value = "图片上传", notes = "图片上传:name=file")
    @RequestMapping(value="upload", method={RequestMethod.POST } )
    public DataResult fileUploadWatermark(HttpServletRequest request, HttpServletResponse response){
        DataResult result = new DataResult();
        String val = FileUtils.uploadImg(request, "file",PathEnum.CUSTOMER_UPLOAD_PATH.getPath());
        result.setData(val);
        return result;
    }

    /**
     * 发送验证码
     * @param phoneNum codeType
     * @return
     */
    @ApiOperation( httpMethod = "GET",value = "发送验证码",notes = "codeType 1-注册验证码类型,2-重置密码类型")
    @RequestMapping(value="identifyingCode", method={RequestMethod.GET } )
    public BaseResult sendPhoneCodeApi(@ApiParam(value = "手机号码" ,required=true ) @RequestParam("phoneNum") String phoneNum,
                                       @ApiParam(value = "验证码类型" ,required=true ) @RequestParam("codeType") int codeType) {
        BaseResult result = new BaseResult();
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < 6;i++){
            int num = (int)(Math.random()*(10));
            sb.append(String.valueOf(num));
        }
        String code = sb.toString();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(Integer.parseInt(code));
        verificationCode.setMobile(phoneNum);
        verificationCode.setType(codeType);
        try {
            if(ConstantsEnum.REGIEST_CODE.getCode() == codeType){
                Customer customer = customerService.selectOne(new EntityWrapper<Customer>().eq("mobile",phoneNum));
                if(null != customer){
                    return new BaseResult(ReturnStatusEnum.CLIENT_ERROR.getValue(),"用户已经存在");
                }else{
                    SendSmsResponse msgRes = SMSUtil.sendMessage(code,phoneNum);
                    System.out.println(msgRes.getCode());
                    System.out.println(msgRes.getMessage());
                    if("OK".equals(msgRes.getCode())){
                        verificationCodeService.insert(verificationCode);
                        return new BaseResult(ReturnStatusEnum.SUCCESS.getValue(),"验证码发送中！");
                    }else{
                        return new BaseResult(ReturnStatusEnum.SYS_ERROR.getValue(),"验证码发送失败！");
                    }
                }
            } else if(ConstantsEnum.RESET_CODE.getCode() == codeType){
                SendSmsResponse res = SMSUtil.sendMessage(code,phoneNum);
                System.out.println(res.getCode());
                if("OK".equals(res.getCode())){
                    verificationCodeService.insert(verificationCode);
                    return new BaseResult(ReturnStatusEnum.SUCCESS.getValue(),"验证码发送中！");
                }else{
                    return new BaseResult(ReturnStatusEnum.SYS_ERROR.getValue(),"验证码发送失败！");
                }
            } else{
                return new BaseResult(ReturnStatusEnum.SYS_ERROR.getValue(),"无效验证码类型！");
            }
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            result.setCode(ReturnStatusEnum.SYS_ERROR.getValue());
            result.setMsg(ReturnStatusEnum.SYS_ERROR.getDesc());
            return result;
        }
    }
}
