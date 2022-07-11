package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.content.entity.Teachplan;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author itcast
 * @since 2022-07-01
 */
public interface TeachplanService extends IService<Teachplan> {

    TeachplanDTO queryTreeNodes(Long courseId, Long companyId);

    TeachplanDTO createTeachPlan(TeachplanDTO dto, Long companyId);

    TeachplanDTO modifyTeachPlan(TeachplanDTO dto, Long companyId);

    boolean removeTeachPlan(Long teachPlanId, Long companyId);
}
