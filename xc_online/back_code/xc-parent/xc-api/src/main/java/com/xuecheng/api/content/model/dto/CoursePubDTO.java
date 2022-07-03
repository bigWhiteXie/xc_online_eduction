package com.xuecheng.api.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程发布
 * </p>
 *
 * @author itcast
 */
@Data
@ApiModel(value="CoursePubDTO", description="课程发布")
public class CoursePubDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "课程标识")
    private Long courseId;

    @ApiModelProperty(value = "机构ID")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "适用人群")
    private String users;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "创建人")
    private String username;

    @ApiModelProperty(value = "大分类")
    private String mt;

    private String mtName;

    @ApiModelProperty(value = "小分类")
    private String st;

    private String stName;

    @ApiModelProperty(value = "课程等级")
    private String grade;

    @ApiModelProperty(value = "教育模式(common普通，record 录播，live直播等）")
    private String teachmode;

    @ApiModelProperty(value = "课程图片")
    private String pic;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程营销信息，json格式")
    private String market;

    @ApiModelProperty(value = "所有课程计划，json格式")
    private String teachplan;

    @ApiModelProperty(value = "教师信息")
    private String teachers;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime changeDate;

    @ApiModelProperty(value = "是否最新课程")
    private Integer isLatest;

    @ApiModelProperty(value = "是否发布(0发布 1取消发布)")
    private Integer isPub;

    @ApiModelProperty(value = "状态（1正常  0删除）")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "收费规则，对应数据字典--203")
    private String charge;

    @ApiModelProperty(value = "现价")
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float priceOld;

    @ApiModelProperty(value = "咨询QQ")
    private String qq;

    @ApiModelProperty(value = "有效性，对应数据字典--204")
    private String valid;

    @ApiModelProperty(value = "课程有效期-开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "课程有效期-结束时间")
    private LocalDateTime endTime;


}
