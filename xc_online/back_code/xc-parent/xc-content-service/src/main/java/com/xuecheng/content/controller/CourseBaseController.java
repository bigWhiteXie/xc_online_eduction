package com.xuecheng.content.controller;

import com.xuecheng.api.content.CourseBaseApi;
import com.xuecheng.api.content.model.vo.CourseBaseVO;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.content.convert.CourseBaseConvert;
import com.xuecheng.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("course")
    @Override
    public CourseBaseDTO createCourseBase(@Valid  @RequestBody  CourseBaseVO courseBaseVO) {
        //1.vo->dto
        CourseBaseDTO dto = CourseBaseConvert.INSTANCE.vo2dto(courseBaseVO);
        //2.设置companyId
        Long companyId = SecurityUtil.getCompanyId();
        dto.setCompanyId(companyId);

        return courseBaseService.createCourseBaseService(dto);
    }

    @GetMapping("course/{courseBaseId}")
    @Override
    public CourseBaseDTO getCourseBase(@PathVariable(value = "courseBaseId") Long courseBaseId) {
        Long companyId = SecurityUtil.getCompanyId();
        return courseBaseService.getCourseBaseById(courseBaseId,companyId);
    }

    @GetMapping("course/commit/{courseBaseId}")
    @Override
    public Boolean commitCourseBase(@PathVariable("courseBaseId") Long courseBaseId) {
        Long companyId = SecurityUtil.getCompanyId();
        return courseBaseService.commitCourse(courseBaseId,companyId);
    }

    @DeleteMapping("course/{courseBaseId}")
    @Override
    public Boolean removeCourseBase(@PathVariable("courseBaseId") Long courseBaseId) {
        return courseBaseService.removeCourseBase(courseBaseId);
    }

    @PutMapping("course")
    @Override
    public CourseBaseDTO modifyCourseBase(@RequestBody CourseBaseVO courseBaseVO) {
        //1.将VO->DTO
        CourseBaseDTO dto = CourseBaseConvert.INSTANCE.vo2dto(courseBaseVO);


        return courseBaseService.modifyCourseBase(dto);
    }
}
