package com.xuecheng.api.content.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@ApiModel(value="CourseBaseVO", description="课程基本信息视图类，用于页面对课程基本信息添加和修改，操作的属性比较有限，不开放的属性不让操作")
public class CourseBaseVO {

    @ApiModelProperty(value = "课程Id")
    private Long courseBaseId;

    @ApiModelProperty(value = "课程名称", required = true)
    @NotBlank(message = "课程名称不能为空")
    private String name;

    @ApiModelProperty(value = "适用人群", required = true)
    @NotBlank(message = "适用人群不能为空")
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @ApiModelProperty(value = "大分类", required = true)
    @NotBlank(message = "大分类不能为空")
    private String mt;

    @ApiModelProperty(value = "小分类", required = true)
    @NotBlank(message = "小分类不能为空")
    private String st;

    @ApiModelProperty(value = "课程等级", required = true)
    @NotBlank(message = "课程等级不能为空")
    private String grade;

    @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
    @NotBlank(message = "教学模式不能为空")
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片", required = true)
    private String pic;


    @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
    @NotBlank(message = "收费模式不能为空")
    private String charge;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
}
