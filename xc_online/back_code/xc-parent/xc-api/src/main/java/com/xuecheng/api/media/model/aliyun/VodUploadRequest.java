package com.xuecheng.api.media.model.aliyun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * <P>
 * 媒体上传请求信息
 * </p>
 *
 */
@Data
@ApiModel("媒体上传请求信息封装类")
public class VodUploadRequest {

   /**
    * 视频标题
    */
   @ApiModelProperty("视频标题")
   private String title;

   /**
    * 视频源文件名（必须带扩展名，且扩展名不区分大小写）
    */
   @ApiModelProperty("视频源文件名")
   private String fileName;

   /**
    * 回调URL
    */
   @ApiModelProperty("回调URL")
   private String callbackURL;

   /**
    * 用户自定义的扩展字段，用于回调时透传返回，最大长度512字节
    */
   @ApiModelProperty("用户自定义的扩展字段，用于回调时透传返回，最大长度512字节")
   private Map<String, String> extend;

}