package com.xuecheng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.system.entity.CourseCategory;
import com.xuecheng.system.entity.ext.CourseCategoryNode;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    List<CourseCategoryNode> selectTreeNodes();

}
