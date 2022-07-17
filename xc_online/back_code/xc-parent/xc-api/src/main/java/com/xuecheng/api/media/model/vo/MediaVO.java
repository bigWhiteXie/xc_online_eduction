package com.xuecheng.api.media.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 媒资信息
 * </p>
 *
 * @author itcast
 */
@Data
@ApiModel(value="MediaVO", description="媒体资源VO对象")
public class MediaVO implements Serializable {

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @NotNull(message = "文件名不能为空")
    private String filename;

    /**
     * 文件类型（文档，音频，视频）
     */
    @ApiModelProperty(value = "文件类型")
    @NotNull(message = "文件类型不能为空")
    private String type;

    /**
     * 标签
     */
    @ApiModelProperty(value = "文件标签")
    private String tags;

    /**
     * 存储源
     */
    @ApiModelProperty(value = "存储源")
    private String bucket;

    /**
     * 文件标识
     */
    @ApiModelProperty(value = "文件唯一标识：七牛云的key，阿里云的videoId")
    @NotNull(message = "文件标识不能为空")
    private String fileId;

    /**
     * 媒资文件访问地址
     */
    @ApiModelProperty(value = "文件地址")
    private String url;

    @ApiModelProperty(value = "视频长度")
    private String timelength;

    /**
     * 备注
     */
    @ApiModelProperty(value = "文件备注")
    private String remark;

}
