package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description 课程管理service
 * @author Mr.M
 * @date 2022/10/8 9:44
 * @version 1.0
 */
public interface CourseBaseInfoService {


 /**
  * @description 课程查询
  * @param params 分页参数
  * @param queryCourseParamsDto 查询条件
  * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
  * @author Mr.M
  * @date 2022/10/8 9:46
 */
  public PageResult<CourseBase>  queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);

}
