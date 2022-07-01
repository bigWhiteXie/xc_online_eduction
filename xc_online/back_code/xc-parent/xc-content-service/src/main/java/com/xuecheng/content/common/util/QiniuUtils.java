package com.xuecheng.content.common.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * 七牛云工具类
 */
@Slf4j
public class QiniuUtils {


    /**
     * 上传到七牛服务中
     * @param accessKey 七牛云accessKey
     * @param secretKey 七牛云secretKey
     * @param bucket    七牛云存储空间
     * @param contentText  静态化字符串内容
     * @param fileKey   上传文件的文件key值
     * @return
     */
    public static boolean upload2Qiniu(String accessKey, String secretKey, String bucket, String contentText, String fileKey) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.huabei());
        //上传管理对象
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            byte[] uploadBytes = contentText.getBytes("utf-8");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, fileKey, upToken);

                //解析上传成功的结果
                if (response != null && response.isOK()) {
                    //解析上传成功的结果
                    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), 
                                                               DefaultPutRet.class);
                    log.info("qiNiu result,  key:{}, hash:{}, response:{}", 
                             putRet.key, putRet.hash, JSON.toJSONString(response));
                    return true;
                } else {
                    return false;
                }

            } catch (QiniuException ex) {
                Response r = ex.response;
                log.error("qiNiu error, response: {}", JSON.toJSONString(r));
            }
        } catch (UnsupportedEncodingException ex) {
            log.error("qiNiu error, response: {}", ex);
        }

        return false;

    }
}