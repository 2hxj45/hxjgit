package com.xz.library.admin.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.xz.library.admin.bean.NoLogin;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xz
 */
@Slf4j
@RestController
public class OssUploadController {

    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKETNAME;

    @PostMapping("uploadFile")
    @ApiOperation("查出新闻的评论")
    @NoLogin
    public String uploadFile(String filedir, InputStream in, String suffix) throws IOException {
        System.out.println("------------>文件名称为:  " + filedir + "." + suffix);
        ossClient  = new OSSClient(ALIYUN_OSS_ENDPOINT, ALIYUN_OSS_ACCESSKEYID, ALIYUN_OSS_ACCESSKEYSECRET);
        URL url = null;
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(in.available());
            objectMetadata.setCacheControl("no-cache");//设置Cache-Control请求头，表示用户指定的HTTP请求/回复链的缓存行为:不经过本地缓存
            objectMetadata.setHeader("Pragma", "no-cache");//设置页面不缓存
            objectMetadata.setContentType(getcontentType(suffix));
            objectMetadata.setContentDisposition("inline;filename=" + filedir + "." + suffix);
            // 上传文件
            ossClient.putObject(bucketName, filedir, in, objectMetadata);

            Date expiration = null;//过期时间
            String[] split = filedir.split("/");
            if(split[0].equals("circle")){// 朋友圈图片,设置URL过期时间为3个月
                expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 90);
            }else{// 头像,设置URL过期时间为10年
                expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 365 * 10);
            }
            // 生成URL
            url = ossClient.generatePresignedUrl(bucketName, filedir, expiration);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url.toString();
    }

        private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        private static String accessKeyId = "<这里是公司的accessKeyId>";
        private static String accessKeySecret = "<这里是公司的accessKeySecret>";
        private static String bucketName = "<这里是公司的bucketName>";
        private static OSSClient ossClient;

        /** 上传文件到阿里云,并生成url
         * @param filedir (key)文件名(不包括后缀)
         * @param in 	文件字节流
         * @param suffix 文件后缀名
         * @return String 生成的文件url
         */

        /**删除图片
         * @param key
         */
        public static void deletePicture(String key){
            ossClient  = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName, key);
            ossClient.shutdown();
        }

        /**
         * Description: 判断OSS服务文件上传时文件的contentType
         * @param suffix 文件后缀
         * @return String HTTP Content-type
         */
        public static String getcontentType(String suffix) {
            System.out.println("------------>文件格式为:  " + suffix);
            if (suffix.equalsIgnoreCase("bmp")) {
                return "image/bmp";
            } else if (suffix.equalsIgnoreCase("gif")) {
                return "image/gif";
            } else if (suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpg")) {
                return "image/jpeg";
            } else if (suffix.equalsIgnoreCase("png")) {
                return "image/png";
            } else if (suffix.equalsIgnoreCase("html")) {
                return "text/html";
            } else if (suffix.equalsIgnoreCase("txt")) {
                return "text/plain";
            } else if (suffix.equalsIgnoreCase("vsd")) {
                return "application/vnd.visio";
            } else if (suffix.equalsIgnoreCase("pptx") || suffix.equalsIgnoreCase("ppt")) {
                return "application/vnd.ms-powerpoint";
            } else if (suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("doc")) {
                return "application/msword";
            } else if (suffix.equalsIgnoreCase("xml")) {
                return "text/xml";
            } else if (suffix.equalsIgnoreCase("mp3")) {
                return "audio/mp3";
            } else if (suffix.equalsIgnoreCase("amr")) {
                return "audio/amr";
            } else {
                return "text/plain";
            }
        }
    }


