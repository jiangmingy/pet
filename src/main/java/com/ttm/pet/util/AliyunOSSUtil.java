package com.ttm.pet.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.*;
import com.ttm.pet.config.OssConstantConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Formatter;
import java.util.UUID;

public class AliyunOSSUtil {
    private final static Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);


    public static String upload(MultipartFile file,String filePath,boolean isWatermark){
        logger.info("=========>OSS文件上传开始："+file.getName());
        String endpoint=OssConstantConfig.JAVA4ALL_END_POINT;
        String accessKeyId=OssConstantConfig.JAVA4ALL_ACCESS_KEY_ID;
        String accessKeySecret=OssConstantConfig.JAVA4ALL_ACCESS_KEY_SECRET;
        String bucketName=OssConstantConfig.JAVA4ALL_BUCKET_NAME1;
//        String fileHost=OssConstantConfig.JAVA4ALL_FILE_HOST;
        String fileHost=filePath;

        if(null == file){
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
//            if(! ossClient.doesBucketExist(bucketName)){
//                ossClient.createBucket(bucketName);
//                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//                ossClient.createBucket(createBucketRequest);
//            }
            //创建文件夹加路径
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String dateStr = format.format(new Date());
//            String fileUrl = fileHost+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            String OriginalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString().replace("-","") + OriginalFilename.substring(OriginalFilename.lastIndexOf("."));
            String fileUrl = fileHost+"/" + filename;
            //上传文件
            PutObjectResult result = ossClient.putObject(bucketName, fileUrl,new ByteArrayInputStream(file.getBytes()));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if(null != result){
                logger.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                if (isWatermark == true){
                    logger.info("==========>加水印");
                    StringBuilder sbStyle = new StringBuilder();
                    Formatter styleFormatter = new Formatter(sbStyle);
                    String styleType = "image/watermark,image_Y3p4U3lzdGVtSW1nL3dhdGVybWFyay5wbmc_eC1vc3MtcHJvY2Vzcz1pbWFnZS9yZXNpemUsUF8yMA,t_90,g_se,x_10,y_10";
                    String targetImage = fileUrl;
                    styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                            BinaryUtil.toBase64String(targetImage.getBytes()),
                            BinaryUtil.toBase64String(bucketName.getBytes()));
                    ProcessObjectRequest request = new ProcessObjectRequest(bucketName, fileUrl, sbStyle.toString());
                    GenericResult processResult = ossClient.processObject(request);
                    String json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
                    processResult.getResponse().getContent().close();
                    System.out.println(json);
                }
//                return PathEnum.ALIYUN_HEAD_URL.getPath() + fileUrl;
                return null;
            }
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return null;
    }
}