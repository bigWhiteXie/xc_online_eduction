package com.xuecheng.api.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="UploadTokenResult", description="上传凭证结果")
public class UploadTokenResult {
    @ApiModelProperty(value = "token类型 1-上传凭证 2-下载凭证 3-管理凭证")
    private String tokenType;

    @ApiModelProperty(value = "命名空间")
    private String scope;

    @ApiModelProperty(value = "文件唯一标识")
    private String key;

    @ApiModelProperty(value = "七牛云token内容")
    private String qnToken;

    @ApiModelProperty(value = "存储区域")
    private String up_region;

    @ApiModelProperty(value = "cdn域名")
    private String domain;

    @ApiModelProperty(value = "token有效时间")
    private int deadline;
}
