package com.xuecheng.media.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
import com.xuecheng.api.media.AliyunApi;
import com.xuecheng.api.media.model.aliyun.VodUploadRequest;
import com.xuecheng.api.media.model.aliyun.VodUploadToken;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.media.common.constant.MediaErrorCode;
import com.xuecheng.media.common.utils.AliyunVODUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.misc.Cleaner;

@RestController
@CrossOrigin
@Slf4j
public class AliYunController implements AliyunApi {
   @Value("${aliyun.accessKeyId}")
   public static String accessKey;

   @Value("${aliyun.accessKeySecret}")
    public static String secretKey;
   @Value("${aliyun.region}")
   public static String region;

   /**
    * 向阿里云请求上传token
    * @param request (需要包含title、fileName)
    * @return VodUploadToken(videoId、uploadAuth(token)、uploadAddress)
    */
   @RequestMapping("media/vod-token")
   @Override
   public VodUploadToken generatorVodToken(@RequestBody VodUploadRequest request) {

      try {
         //1.初始化客户端
         DefaultAcsClient client = AliyunVODUtil.initVodClient(region, accessKey, secretKey);

         //2.构建请求并发送，得到响应
         CreateUploadVideoResponse response = AliyunVODUtil.createUploadVideo(client, request.getTitle(), request.getFileName());

         //3.解析响应结果
         VodUploadToken uploadToken = new VodUploadToken();
         uploadToken.setUploadAuth(response.getUploadAuth());
         uploadToken.setVideoId(response.getVideoId());
         uploadToken.setUploadAddress(response.getUploadAddress());
         uploadToken.setRequestId(response.getRequestId());

         return uploadToken;
      }catch (Exception e){
         System.out.println(e.getMessage());
         ExceptionCast.cast(MediaErrorCode.E_140011);
      }
      return null;
   }

   /**
    * 刷新上传token
    * @param videoId
    * @return
    */
   @GetMapping("media/refresh-vod-token/{videoId}")
   @Override
   public VodUploadToken refreshToken(@PathVariable String videoId) {
      try {
         //1.初始化客户端
         DefaultAcsClient client = AliyunVODUtil.initVodClient(region, accessKey, secretKey);

         //2.发送刷新token的请求并得到响应
         RefreshUploadVideoResponse response = AliyunVODUtil.refreshUploadVideo(client, videoId);

         //3.解析响应结果
         VodUploadToken uploadToken = new VodUploadToken();
         uploadToken.setUploadAuth(response.getUploadAuth());
         uploadToken.setVideoId(response.getVideoId());
         uploadToken.setUploadAddress(response.getUploadAddress());
         uploadToken.setRequestId(response.getRequestId());

         return uploadToken;
      }catch (Exception e){
         ExceptionCast.cast(MediaErrorCode.E_140015);
      }
      return null;
   }
}
