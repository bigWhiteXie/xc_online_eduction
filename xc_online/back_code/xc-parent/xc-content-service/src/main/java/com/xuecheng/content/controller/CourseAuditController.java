package com.xuecheng.content.controller;

import com.xuecheng.api.content.CourseAuditApi;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.api.content.model.vo.CourseAuditVO;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.content.service.CourseAuditService;
import com.xuecheng.content.service.CourseBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseAuditController implements CourseAuditApi {
    @Autowired
    private CourseAuditService courseAuditService;


    @PostMapping("m/course/list")
    @Override
    public PageVO<CourseBaseDTO> queryCourseBaseList(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams) {
        return courseAuditService.findCoursePage(queryModel,pageRequestParams);
    }

    @PostMapping("m/courseReview/approve")
    @Override
    public boolean auditCourse(@RequestBody CourseAuditVO auditVO) {

        return courseAuditService.auditCourse(auditVO);
    }
}
