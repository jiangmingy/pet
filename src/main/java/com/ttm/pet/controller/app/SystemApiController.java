package com.ttm.pet.controller.app;

import com.ttm.pet.enums.PathEnum;
import com.ttm.pet.model.pojo.DataResult;
import com.ttm.pet.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/app/system",tags = "app-system",description = "通用接口")
@RequestMapping("/app/system")
public class SystemApiController {
    private final static Logger logger = LoggerFactory.getLogger(SystemApiController.class);

    @ApiOperation( value = "图片上传", notes = "图片上传:name=file")
    @RequestMapping(value="upload", method={RequestMethod.POST } )
    public DataResult fileUploadWatermark(HttpServletRequest request, HttpServletResponse response){
        DataResult result = new DataResult();
        String val = FileUtils.uploadImg(request, "file",PathEnum.CUSTOMER_UPLOAD_PATH.getPath());
        result.setData(val);
        return result;
    }
}
