package com.xuecheng.api.content;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.vo.CourseBaseVO;
import com.xuecheng.api.content.model.vo.CourseTeacherVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程教师信息管理 Api",tags = "课程教师信息管理",description = "课程教师信息管理")
public interface CourseTeacherApi {

    @ApiOperation("保存课程教师信息")
    CourseBaseDTO createCourseTeacher(CourseTeacherVo teacherVo);
}
