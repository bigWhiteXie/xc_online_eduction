package com.xuecheng.content.controller;

import com.xuecheng.api.content.CourseBaseApi;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程基本信息 前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
public class CourseBaseController implements CourseBaseApi {

    @Autowired
    private CourseBaseService  courseBaseService;



    @PostMapping("course/list")
    @Override
    public PageVO<CourseBaseDTO> queryCourseBaseList(@RequestBody  QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams) {
        return courseBaseService.queryCourseBasePage(queryModel,pageRequestParams);
    }
}
