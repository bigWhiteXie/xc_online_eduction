package com.xuecheng.api.content;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *     课程基本信息 Api 接口
 * </p>
 */
@Api(value = "课程基本信息管理 Api",tags = "课程基本信息管理",description = "课程基本信息业务管理")
public interface CourseBaseApi {

    @ApiOperation("查询全部课程信息")
    PageVO<CourseBaseDTO> queryCourseBaseList(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams);
}