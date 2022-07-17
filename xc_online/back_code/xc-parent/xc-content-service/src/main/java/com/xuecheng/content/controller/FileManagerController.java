package com.xuecheng.content.controller;

import com.xuecheng.api.content.FileManagerApi;
import com.xuecheng.api.content.model.dto.UploadTokenResult;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.content.common.constant.ContentErrorCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("common")
public class FileManagerController implements FileManagerApi {

    @Value("${file.service.url}")
    private String url;
    @Value("${file.service.bucket}")
    private String bucket;
    @Value("${file.service.upload.region}")
    private String region;
    @Value("${cdn.domain}")
    private String domain;
    @Value("${file.token.type}")
    private String tokenType;
    @Value("${file.token.deadline}")
    private int deadline;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("qnUploadToken")
    @Override
    public UploadTokenResult qiniuUploadToken() {
        //1.构建请求参数
        HashMap<String, Object> requestBody = new HashMap<>();
        //{
        //   "tokenType":"1",
        //   "scope":"xc140-static-imgs",
        //   "deadline":3600,
        //   "key":"111xxxeeee"
        // }
        requestBody.put("tokenType",tokenType);
        requestBody.put("scope",bucket);
        requestBody.put("deadline",deadline);
        String fileKey = UUID.randomUUID().toString() + RandomStringUtils.randomAlphanumeric(16);
        requestBody.put("key",fileKey);

        //2.发送 获取上传凭证请求
        ResponseEntity<Map> response = restTemplate.postForEntity(url+"generatetoken?origin=qiniu", requestBody, Map.class);

        //3.校验响应是否正确
        HttpStatus statusCode = response.getStatusCode();
        if (!(HttpStatus.OK == statusCode)) {
            ExceptionCast.cast(ContentErrorCode.E_120025);
        }
        //4.得到响应体
        Map body = response.getBody();
        Integer code = (Integer) body.get("code");
        if(code != 0){
            ExceptionCast.cast(ContentErrorCode.E_120025);
        }
        String token = (String) body.get("result");

        //5.封装成tokenResult返回
        UploadTokenResult tokenResult = new UploadTokenResult();
        tokenResult.setQnToken(body.get("result").toString());
        tokenResult.setDeadline(deadline);
        tokenResult.setDomain(domain);
        tokenResult.setTokenType(tokenType);
        tokenResult.setKey(fileKey);
        tokenResult.setScope(bucket);
        tokenResult.setUp_region(region);

        return tokenResult;
    }

}
