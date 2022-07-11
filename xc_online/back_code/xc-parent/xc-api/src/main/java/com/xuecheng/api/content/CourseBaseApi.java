package com.xuecheng.api.content;

import com.xuecheng.api.content.model.vo.CourseBaseVO;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *     课程基本信息 Api 接口
 * </p>
 */
@Api(value = "课程基本信息管理 Api",tags = "课程基本信息管理",description = "课程基本信息业务管理")
public interface CourseBaseApi {

    @ApiOperation("条件分页查询课程信息")
    PageVO<CourseBaseDTO> queryCourseBaseList(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams);

    @ApiOperation("保存课程基础信息")
    CourseBaseDTO createCourseBase(CourseBaseVO courseBaseVO);

    @ApiOperation("查询课程基础信息")
    @ApiImplicitParam(name = "courseBaseId", value = "课程基本信息ID", required = true, dataType = "Long", paramType = "path", example = "1")
    CourseBaseDTO getCourseBase(Long courseBaseId);

    @ApiOperation("删除课程基础信息")
    @ApiImplicitParam(name = "courseBaseId", value = "课程基本信息ID", required = true, dataType = "Long", paramType = "path", example = "1")
    Boolean removeCourseBase(Long courseBaseId);

    @ApiOperation("修改课程基础信息")
    CourseBaseDTO modifyCourseBase(CourseBaseVO courseBaseVO);


}