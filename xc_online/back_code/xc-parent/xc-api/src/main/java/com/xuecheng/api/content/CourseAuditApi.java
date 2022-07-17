package com.xuecheng.api.content;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.api.content.model.vo.CourseAuditVO;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程审核管理 Api",tags = "课程审核管理",description = "运营平台对已提交的课程基本信息的业务管理")
public interface CourseAuditApi {

    @ApiOperation("条件分页查询已提交的课程")
    PageVO<CourseBaseDTO> queryCourseBaseList(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams);

    @ApiOperation("课程审核")
    boolean auditCourse(CourseAuditVO auditVO);
}
