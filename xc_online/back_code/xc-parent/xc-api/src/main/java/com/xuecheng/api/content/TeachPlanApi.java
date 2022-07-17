package com.xuecheng.api.content;

import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.api.content.model.vo.TeachplanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "教学计划管理 Api",tags = "教学计划管理",description = "教学计划业务管理")
public interface TeachPlanApi {

    @ApiOperation("查询教学计划")
    @ApiImplicitParam(name = "courseId",value = "课程基础信息id",required = true,dataType = "Long",paramType = "path")
    TeachplanDTO queryTreeNodesByCourseId(Long courseId);

    @ApiOperation("新增或修改教学计划")
    TeachplanDTO modifyOrCreateTeachPlan(TeachplanVO vo);

    @ApiOperation("删除教学计划章节")
    @ApiImplicitParam(name = "teachPlanId",value = "教学计划id",required = true,dataType = "Long",paramType = "path")
    Boolean removeTeachPlanById(Long teachPlanId);
}
