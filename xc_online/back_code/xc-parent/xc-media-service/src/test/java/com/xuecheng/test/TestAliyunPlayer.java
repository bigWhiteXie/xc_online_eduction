package com.xuecheng.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


public class TestAliyunPlayer {
    private String regionId = "cn-beijing";
    private String accessKeyId="LTAI5t5ewf4Mvwu1DjhMX2HW";
    private String accessKeySecret="RY0yibtOZA4BW3FoBPzwhecbEHvXeQ";



    public static DefaultAcsClient initVodClient(String regionId, String accessKeyId, String accessKeySecret) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    @Test
    public void testUploadToken() throws Exception {

        // 1.初始化客户端
        DefaultAcsClient client = initVodClient("cn-beijing", accessKeyId, accessKeySecret);

        // 2.构建请求对象
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle("this is a sample2");
        request.setFileName("filename.mp4");



        // 3.获得响应对象
        CreateUploadVideoResponse response = client.getAcsResponse(request);


        // 4.解析响应数据
        try {
            System.out.print("VideoId = " + response.getVideoId() + "\n");
            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");

    }


    @Test
    public void testPlayURL() throws Exception {

        // 1.初始化客户端
        DefaultAcsClient client = initVodClient("cn-beijing", accessKeyId, accessKeySecret);

        // 2.创建请求对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("0d6ef0b787c442088c45aadd2064d714");

        // 3.获得响应对象
        GetPlayInfoResponse response = client.getAcsResponse(request);


        // 4.解析响应数据
        try {
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");

    }
}
