package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.content.entity.CourseBase;

import java.util.List;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author itcast
 * @since 2022-07-01
 */
public interface CourseBaseService extends IService<CourseBase> {
    /**
     * 查询课程基本信息
     * @return TbUser - 用户信息
     */


    PageVO<CourseBaseDTO> queryCourseBasePage(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams);

    CourseBaseDTO createCourseBaseService(CourseBaseDTO dto);

    CourseBaseDTO getCourseBaseById(Long courseBaseId);

    CourseBaseDTO modifyCourseBase(CourseBaseDTO dto);

    Boolean removeCourseBase(Long courseBaseId);
}
