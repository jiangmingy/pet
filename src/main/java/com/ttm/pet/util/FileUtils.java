package com.ttm.pet.util;

import com.ttm.pet.enums.PathEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileUtils {
    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String uploadImg(HttpServletRequest request, String name,String filePath,boolean isWatermark){
        if(! (request instanceof MultipartHttpServletRequest)){
            return "";
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mFile = multipartRequest.getFile(name);
        if (!mFile.isEmpty()) {
            int width=0;
            int height = 0;
            try{
                BufferedImage bufferedImage = ImageIO.read(mFile.getInputStream());
                width = bufferedImage.getWidth();
                height = bufferedImage.getHeight();
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                //上传到OSS
                String uploadUrl = AliyunOSSUtil.upload(mFile,filePath,isWatermark);
                return uploadUrl+"?"+width+","+height;
            } catch (Exception e) {
                logger.error("上传到oss错误"+e.getMessage());
                return "";
            }
        }
        return "";
    }

    public static boolean deleteImg(String filename){
        try {
            File file = new File(PathEnum.FAILE_PATH.getPath()+"/"+filename);
            if(file.exists()&&file.isFile()){
                file.delete();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除文件失败，文件:{}",filename);
            return false;
        }
    }
}
