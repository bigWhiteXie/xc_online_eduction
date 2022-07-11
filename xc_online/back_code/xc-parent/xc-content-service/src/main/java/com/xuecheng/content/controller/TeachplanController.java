package com.xuecheng.content.controller;

import com.xuecheng.api.content.TeachPlanApi;
import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.api.content.model.vo.TeachplanVO;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.content.convert.TeachPlanConvert;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程计划 前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
@RequestMapping("teachplan")
public class TeachplanController implements TeachPlanApi {

    @Autowired
    private TeachplanService  teachplanService;

    @GetMapping("{courseId}/tree-nodes")
    @Override
    public TeachplanDTO queryTreeNodesByCourseId(@PathVariable(value = "courseId") Long courseId) {
        Long companyId = SecurityUtil.getCompanyId();
        return teachplanService.queryTreeNodes(courseId,companyId);
    }

    @PostMapping
    @Override
    public TeachplanDTO modifyOrCreateTeachPlan(@RequestBody TeachplanVO vo) {
        //1.将vo转化为dto,获取companyId
        TeachplanDTO dto = TeachPlanConvert.INSTANCE.vo2dto(vo);
        Long companyId = SecurityUtil.getCompanyId();
        //2.根据id判断是创建还是修改业务
        Long id = dto.getId();
        if(ObjectUtils.isEmpty(id)){
            return teachplanService.createTeachPlan(dto,companyId);
        }
        return teachplanService.modifyTeachPlan(dto,companyId);
    }

    @DeleteMapping("{teachPlanId}")
    @Override
    public Boolean removeTeachPlanById(@PathVariable("teachPlanId") Long teachPlanId) {
        Long companyId = SecurityUtil.getCompanyId();
        return teachplanService.removeTeachPlan(teachPlanId,companyId);
    }
}
