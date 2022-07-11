package com.xuecheng.api.system;

import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.common.domain.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@Api(tags = "课程分类api服务")
public interface CourseCategoryApi {

    @ApiOperation("课程分类树形结构查询")
    List<CourseCategoryDTO> queryTreeNodes();

    @ApiOperation("根据课程分类id查询-远端服务调用")
    RestResponse<CourseCategoryDTO> getCourseCategoryById4s(String id);

}
