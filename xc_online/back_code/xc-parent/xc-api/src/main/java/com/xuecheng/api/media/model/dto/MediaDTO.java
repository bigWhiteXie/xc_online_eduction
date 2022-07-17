package com.xuecheng.api.media.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value="MediaDTO", description="媒资信息")
public class MediaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    private Long companyId;

    @ApiModelProperty(value = "机构名称")
    private String companyName;

    @ApiModelProperty(value = "文件名称")
    private String filename;

    @ApiModelProperty(value = "文件类型（文档，音频，视频）")
    private String type;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "存储源")
    private String bucket;

    @ApiModelProperty(value = "文件标识")
    private String fileId;

    @ApiModelProperty(value = "媒资文件访问地址")
    private String url;

    private String timelength;

    @ApiModelProperty(value = "上传人")
    private String username;

    @ApiModelProperty(value = "上传时间")
    private String createDate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime changeDate;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "审核意见")
    private String auditMind;


}
