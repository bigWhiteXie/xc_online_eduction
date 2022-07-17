package com.xuecheng.api.content.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程计划
 */
@Data
@ApiModel(value="TeachplanVO", description="课程计划")
public class TeachplanVO implements Serializable {
    @ApiModelProperty(value = "课程计划Id")
    private Long teachPlanId;

    @ApiModelProperty(value = "课程标识", required = true)
    @NotNull(message = "课程id不能为空")
    private Long courseId;

    @ApiModelProperty(value = "课程计划名称", required = true)
    @NotNull(message = "课程名称不能为空")
    private String pname;
    @ApiModelProperty(value = "课程计划父级Id")
    private Long parentid;
    @ApiModelProperty(value = "层级，分为1、2、3级")
    private Integer grade;
    @ApiModelProperty(value = "课程计划资源类型", required = true)
    private String mediaType;
    @ApiModelProperty(value = "开始直播时间(仅限直播类型,直播时不能为空)")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "开始直播时间(仅限直播类型,直播时不能为空)")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "排序字段")
    private Integer orderby;
    @ApiModelProperty(value = "是否支持试学或预览, 1是免费，0或空是收费")
    private String isPreview;
}