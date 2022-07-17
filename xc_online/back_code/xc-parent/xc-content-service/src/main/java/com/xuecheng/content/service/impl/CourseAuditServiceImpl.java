package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.api.content.model.vo.CourseAuditVO;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.enums.content.CourseAuditEnum;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.content.common.constant.ContentErrorCode;
import com.xuecheng.content.convert.CourseBaseConvert;
import com.xuecheng.content.entity.CourseBase;
import com.xuecheng.content.service.CourseAuditService;
import com.xuecheng.content.service.CourseBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseAuditServiceImpl implements CourseAuditService {
    @Autowired
    private CourseBaseService courseBaseService;

    /**
     * 1.校验关键字段
     *      pageNo、pageSize>0
     * 2.创建分页查询对象
     * 3.
     * @param queryModel
     * @param pageRequestParams
     * @return
     */
    @Override
    public PageVO<CourseBaseDTO> findCoursePage(QueryCourseBaseModel queryModel, PageRequestParams pageRequestParams) {
        //1.验证关键字段
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
        Page<CourseBase> pageResult = courseBaseService.lambdaQuery().
                like(!ObjectUtils.isEmpty(queryModel.getCourseName()), CourseBase::getName, queryModel.getCourseName()).
                eq(!ObjectUtils.isEmpty(queryModel.getAuditStatus()), CourseBase::getAuditStatus, queryModel.getAuditStatus()).
                orderByDesc(CourseBase::getCreateDate).
                page(page);

        //4.封装成pageVo
        List<CourseBase> records = pageResult.getRecords();
        List<CourseBaseDTO> dtoList = CourseBaseConvert.INSTANCE.entities2dtos(records);

        PageVO<CourseBaseDTO> pageVO = new PageVO<>();
        pageVO.setPage((int) pageResult.getCurrent());
        pageVO.setPageSize((int) pageResult.getSize());
        pageVO.setCounts(pageResult.getTotal());
        pageVO.setItems(dtoList);


        return pageVO;
    }

    /**
     * 1.校验关键参数
     *      VO中校验完毕
     * 2.校验业务数据
     *     该课程记录只能是未提交
     * 3.修改课程审核字段
     * @param auditVO
     * @return
     */
    @Transactional
    @Override
    public boolean auditCourse(CourseAuditVO auditVO) {
        CourseBase courseBase = courseBaseService.getById(auditVO.getCourseBaseId());

        if(ObjectUtils.isEmpty(courseBase)){
            ExceptionCast.cast(ContentErrorCode.E_120013);
        }
        if(!courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_COMMIT_STATUS.getCode())){
            ExceptionCast.cast(ContentErrorCode.E_120015);
        }

        boolean update = courseBaseService.lambdaUpdate().
                eq(CourseBase::getId, auditVO.getCourseBaseId()).
                set(CourseBase::getAuditStatus, auditVO.getAuditStatus()).
                set(CourseBase::getAuditMind, auditVO.getAuditMind()).
                set(CourseBase::getAuditDate, LocalDateTime.now()).
                update();
        return update;
    }
}
