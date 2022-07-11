package com.xuecheng.api.content;

import com.xuecheng.api.content.model.dto.UploadTokenResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <P>
 * 		课程管理服务获得外部接口 Api
 * </p>
 */
    @Api(value = "文件、图片上传及 短信服务 服务 Api ",tags = "获得外部接口 Api")
public interface FileManagerApi {

	@ApiOperation(value = "获取七牛云上传凭证, 拿到凭证客户端上传")
	UploadTokenResult qiniuUploadToken();
}