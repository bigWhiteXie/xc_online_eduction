package com.xuecheng.api.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程基本信息
 * </p>
 *
 * @author itcast
 */
@Data
@ApiModel(value="CourseBaseDTO", description="课程基本信息")
public class CourseBaseDTO implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long courseBaseId;

    @ApiModelProperty(value = "机构ID")
    private Long companyId;

    @ApiModelProperty(value = "机构名称")
    private String companyName;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "适用人群")
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @ApiModelProperty(value = "大分类")
    private String mt;

    @ApiModelProperty(value = "课程大类名称")
    private String mtName;

    @ApiModelProperty(value = "小分类")
    private String st;

    @ApiModelProperty(value = "课程小类名称")
    private String stName;

    @ApiModelProperty(value = "课程等级")
    private String grade;

    @ApiModelProperty(value = "教育模式(common普通，record 录播，live直播等）")
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片")
    private String pic;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "审核意见")
    private String auditMind;

    @ApiModelProperty(value = "审核次数")
    private Integer auditNums;


    @ApiModelProperty(value = "是否删除：1为未删除，0为删除")
    private Integer status;

    @ApiModelProperty(value = "课程发布标识")
    private Long coursePubId;


}
