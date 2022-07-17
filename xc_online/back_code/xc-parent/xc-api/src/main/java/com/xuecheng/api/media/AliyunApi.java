package com.xuecheng.api.media;

import com.xuecheng.api.media.model.aliyun.VodUploadRequest;
import com.xuecheng.api.media.model.aliyun.VodUploadToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "阿里云点播Api",tags = "阿里云点播接口",description = "阿里云点播接口")
public interface AliyunApi {
    @ApiOperation("生成上传视频的token")
    public VodUploadToken generatorVodToken(VodUploadRequest request);

    @ApiOperation("刷新上传视频的token")
    public VodUploadToken refreshToken(String videoId);

}
