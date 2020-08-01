package com.jacc.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.jacc.commonutils.R;
import com.jacc.serviceoss.service.OssService;
import com.jacc.serviceoss.utils.Global;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String endpoint = Global.END_POINT;
            String accessKeyId = Global.ACCESS_KEY_ID;
            String accessKeySecret = Global.ACCESS_KEY_SECRET;
            String bucketName = Global.BUCKET_NAME;

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //文件名
            String fileName = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().trim();
            //根据日期分类
            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date + "/" +uuid + fileName;
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取上传文件后的url
            //https://kase.oss-cn-hangzhou.aliyuncs.com/mm04.png
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e) {
            return null;
        }
    }
}
