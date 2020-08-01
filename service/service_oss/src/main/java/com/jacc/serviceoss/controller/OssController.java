package com.jacc.serviceoss.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.jacc.commonutils.R;
import com.jacc.serviceoss.service.OssService;
import com.jacc.serviceoss.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/serviceOss/oss")
@CrossOrigin //跨域
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("/uploadFile")
    public R uploadFile(MultipartFile file) {
        try {
            String url = ossService.uploadFile(file);
            return R.ok().data("url",url);
        }catch (Exception e){
            return R.error();
        }
    }
}
