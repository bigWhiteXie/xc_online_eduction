package com.xuecheng.system.controller;

import com.xuecheng.api.system.CourseCategoryApi;
import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.system.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
public class CourseCategoryController implements CourseCategoryApi {

    @Autowired
    private CourseCategoryService  courseCategoryService;

    @GetMapping("course-category/tree-nodes")
    public List<CourseCategoryDTO> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes();
    }

    @GetMapping("l/course-category/{id}")
    public RestResponse<CourseCategoryDTO> getCourseCategoryById4s(@PathVariable String id) {
        return courseCategoryService.getCourseCategoryById4s(id);
    }
}
