package com.xuecheng.api.content.model.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据审核状态和课程名称进行条件查询
 */
@ApiModel("课程基础条件查询信息封装对象")
@Data
public class QueryCourseBaseModel {
    @ApiModelProperty("课程审核状态")
    private String auditStatus;

    @ApiModelProperty("课程名称")
    private String courseName;
}
