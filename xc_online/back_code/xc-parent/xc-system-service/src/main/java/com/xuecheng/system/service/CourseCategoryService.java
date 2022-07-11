package com.xuecheng.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.system.entity.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author itcast
 * @since 2021-10-07
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 课程分类树形结构查询
     * @return
     */
    List<CourseCategoryDTO> queryTreeNodes();


    /**
     * 根据课程分类id查询-远端服务调用
     * @param id
     * @return
     */
    RestResponse<CourseCategoryDTO> getCourseCategoryById4s(String id);
}
