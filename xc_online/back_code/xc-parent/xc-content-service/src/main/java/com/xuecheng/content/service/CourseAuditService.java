package com.xuecheng.content.service;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.api.content.model.vo.CourseAuditVO;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;

public interface CourseAuditService {
    PageVO<CourseBaseDTO> findCoursePage(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams);

    boolean auditCourse(CourseAuditVO auditVO);
}
