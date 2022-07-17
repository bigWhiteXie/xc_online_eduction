package com.xuecheng.media.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyRequest;
import com.aliyuncs.kms.model.v20160120.GenerateDataKeyResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.springframework.util.ObjectUtils;

import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsRequest;
import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsResponse;
/**
 * <p></p>
 *
 * @Description:
 */
public class AliyunVODUtil {

    private AliyunVODUtil() {
    }

    /**
     * 初始化VOD客户端对象
     * @param regionId       --区域id（华北2：cn-beijing，华东2：cn-shanghai）
     * @param accessKeyId    --秘钥的ackId
     * @param accessKeySecret--秘钥的ackSecret
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient(String regionId, String accessKeyId, String accessKeySecret) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    /**
     * 获取视频上传地址和凭证
     * @param client 发送请求客户端
     * @param fileTitle 流媒体文件的显示名称
     * @param fileName  流媒体文件的原始名称
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client,String fileTitle,String fileName) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(fileTitle);
        request.setFileName(fileName);
        return client.getAcsResponse(request);
    }


    /**
     * 刷新视频上传凭证
     * @param client 发送请求客户端
     * @return RefreshUploadVideoResponse 刷新视频上传凭证响应数据
     * @throws Exception
     */
    public static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient client, String videoId) throws Exception {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }


    /**
     * 获得媒资播放请求对象
     * @param client
     * @param videoId
     * @return
     * @throws Exception
     */
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String videoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }


    /**
     * 删除视频
     * @param client 发送请求客户端
     * @param videoIds 流媒体文件的fileId值
     * @return DeleteVideoResponse 删除视频响应数据
     * @throws Exception
     */
    public static void deleteVideo(DefaultAcsClient client,String... videoIds) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        if (ObjectUtils.isEmpty(videoIds)) {
            return ;
        }
        //支持传入多个视频ID，多个用逗号分隔
        String videoIdsStr = String.join(",", videoIds);
        request.setVideoIds(videoIdsStr);
        client.getAcsResponse(request);
    }

    /**
     * 提交转码作业
     * @param client
     * @return
     * @throws Exception
     */
    public static SubmitTranscodeJobsResponse submitTranscodeJobs(DefaultAcsClient client,String videoId) throws Exception {
        SubmitTranscodeJobsRequest request = new SubmitTranscodeJobsRequest();
        //需要转码的视频ID
        request.setVideoId(videoId);
        //转码模板ID
        request.setTemplateGroupId("4effb8875696b7f17e4d43178bd49531");
        //构建需要替换的水印参数(只有需要替换水印相关信息才需要构建)
        JSONObject overrideParams = buildOverrideParams();
        //覆盖参数，暂只支持水印部分参数替换(只有需要替换水印相关信息才需要传递)
        request.setOverrideParams(overrideParams.toJSONString());
        //构建标准加密配置参数(只有标准加密才需要构建)
        JSONObject encryptConfig = buildEncryptConfig(client);
        //HLS标准加密配置(只有标准加密才需要传递)
        request.setEncryptConfig(encryptConfig.toJSONString());
        return client.getAcsResponse(request);
    }




    /**
     * 构建HLS标准加密的配置信息
     * @return
     * @throws ClientException
     */
    public static JSONObject buildEncryptConfig(DefaultAcsClient client) throws ClientException {
        //点播给用户在KMS(密钥管理服务)中的Service Key，可在用户密钥管理服务对应的区域看到描述为vod的service key
        String serviceKey = "<您的Service Key>";
        //随机生成一个加密的密钥，返回的response包含明文密钥以及密文密钥
        //视频标准加密只需要传递密文密钥即可
        //注意：KMS Client建议单独初始化来保证正确的接入区域，可参考VOD初始化方式，传入正确的KMS服务区域。
        GenerateDataKeyResponse response = generateDataKey(client, serviceKey);
        JSONObject encryptConfig = new JSONObject();
        //解密接口地址，该参数需要将每次生成的密文密钥与接口URL拼接生成，表示每个视频的解密的密文密钥都不一样
        //至于Ciphertext这个解密接口参数的名称，用户可自行制定，这里只作为参考参数名称
        encryptConfig.put("DecryptKeyUri", "http://example.aliyundoc.com/decrypt?" +
                "Ciphertext=" + response.getCiphertextBlob());
        //密钥服务的类型，目前只支持KMS
        encryptConfig.put("KeyServiceType", "KMS");
        //密文密钥
        encryptConfig.put("CipherText", response.getCiphertextBlob());
        return encryptConfig;
    }

    /**
     * 1、构建覆盖参数，目前只支持图片水印文件地址、文字水印的内容覆盖；
     * 2、需要替换的水印信息对应水印ID必须是关联在指定的模板ID(即TranscodeTemplateId)中；
     * 3、不支持通过媒体处理接口去增加一个没有关联上的水印
     * 注意：图片水印的文件存储源站需要和发起转码的视频存储源站一致
     * @return
     */
    public static JSONObject buildOverrideParams() {
        JSONObject overrideParams = new JSONObject();
        JSONArray watermarks = new JSONArray();
        //图片水印文件地址替换
        JSONObject watermark1 = new JSONObject();
        //模板上面关联需要替换的水印文件图片水印ID
        watermark1.put("WatermarkId", "2ea587477c5a1bc8b57****");
        //需要替换成对应图片水印文件的OSS地址，水印文件存储源站需要和视频存储源站一致
        watermark1.put("FileUrl", "https:192.168.0.1/16");

        //文字水印内容替换
        JSONObject watermark2 = new JSONObject();
        //模板上面关联需要替换内容的文字水印ID
        watermark2.put("WatermarkId", "d297ba31ac5242d207****");
        //需要替换成对应的内容
        watermark2.put("Content", "用户ID：6****");
        watermarks.add(watermark2);
        overrideParams.put("Watermarks", watermarks);
        return overrideParams;
    }

    /**
     * 生成加密需要的密钥，response中包含密文密钥和明文密钥，用户只需要将密文密钥传递给点播即可
     * 注意：KeySpec 必须传递AES_128，且不能设置NumberOfBytes
     * @param client KMS-SDK客户端
     * @param serviceKey 点播提供生成密钥的service key，在用户的密钥管理服务中可看到描述为vod的加密key
     * @return
     * @throws ClientException
     */
    public static GenerateDataKeyResponse generateDataKey(DefaultAcsClient client, String serviceKey) throws ClientException {
        GenerateDataKeyRequest request = new GenerateDataKeyRequest();
        request.setKeyId(serviceKey);
        request.setKeySpec("AES_128");
        return client.getAcsResponse(request);
    }
}
