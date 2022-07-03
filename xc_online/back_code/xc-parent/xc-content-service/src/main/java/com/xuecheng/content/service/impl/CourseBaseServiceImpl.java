package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.common.util.StringUtil;
import com.xuecheng.content.convert.CourseBaseConvert;
import com.xuecheng.content.entity.CourseBase;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {


    /**
     * 分页条件查询课程基础信息
     * 1.判断是否需要开启事务
     * 2.对参数进行校验
     *    PageRequestParams：pageNo和pageSize > 0
     *    queryModel: 若不为空则参与条件查询，无需校验
     * 3.构建page分页对象
     * 4.构建queryWrapper，对queryModel进行条件筛选
     * 5.对数据库进行分页条件查询，返回实体类对象
     * 6.将Po转为DTO对象并封装大奥pageVO中
     * @param queryModel
     * @param pageRequestParams
     * @return
     */
    @Override
    public PageVO<CourseBaseDTO> queryCourseBasePage(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams) {
        //1. 获取公司id
        Long companyId = SecurityUtil.getCompanyId();
        // 2.对参数进行校验
        //  PageRequestParams：pageNo和pageSize > 0
          if(pageRequestParams.getPageNo() < 1){
              pageRequestParams.setPageNo(PageRequestParams.DEFAULT_PAGE_NUM);
          }
          if(pageRequestParams.getPageSize() < 1){
              pageRequestParams.setPageSize(PageRequestParams.DEFAULT_PAGE_SIZE);
          }
        Long pageNo = pageRequestParams.getPageNo();
        Integer pageSize = pageRequestParams.getPageSize();

        // 3.构建page分页对象
        Page<CourseBase> page = new Page<>(pageNo, pageSize);

        // 4.构建queryWrapper，对queryModel进行分页条件查询
        Page<CourseBase> queryPage = this.lambdaQuery().
                like(StringUtil.isNotEmpty(queryModel.getCourseName()), CourseBase::getName, queryModel.getCourseName()).
                eq(StringUtil.isNotEmpty(queryModel.getAuditStatus()), CourseBase::getAuditStatus, queryModel.getAuditStatus()).
                eq(CourseBase::getCompanyId,companyId).
                orderByDesc(CourseBase::getCreateDate).
                page(page);

       // 6.将Po转为DTO对象并封装大奥pageVO中
        List<CourseBase> records = queryPage.getRecords();
        List<CourseBaseDTO> dtos = Collections.EMPTY_LIST;

        if(!CollectionUtils.isEmpty(records)){
            CourseBaseConvert convert = CourseBaseConvert.INSTANCE;
            dtos = convert.entities2dtos(records);
        }

        //7.封装pageVO并返回
        PageVO pageVO = new PageVO(dtos, page.getTotal(), pageNo, pageSize);
        return pageVO;

    }
}
