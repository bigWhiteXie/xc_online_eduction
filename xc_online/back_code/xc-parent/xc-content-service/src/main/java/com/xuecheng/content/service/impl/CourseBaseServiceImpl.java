package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.qo.QueryCourseBaseModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.enums.common.AuditEnum;
import com.xuecheng.common.enums.content.CourseAuditEnum;
import com.xuecheng.common.enums.content.CourseChargeEnum;
import com.xuecheng.common.enums.content.TeachPlanEnum;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.common.util.StringUtil;
import com.xuecheng.content.common.constant.ContentErrorCode;
import com.xuecheng.content.convert.CourseBaseConvert;
import com.xuecheng.content.entity.CourseBase;
import com.xuecheng.content.entity.CourseMarket;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.properties.QiNiuProperties;
import com.xuecheng.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    CourseMarketServiceImpl courseMarketService;

    @Autowired
    CourseBaseMapper courseBaseMapper;

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

    /**
     * 1.校验参数
     *    companyId由securityUtil校验完成
     *    判断教学模式是否合法(收费必须有价格)
     *    其他基本参数在VO以及校验完毕
     * 2.设置默认参数
     *    audit_status 手动设置为未审核
     *    createDate mp帮我们设置
     *    status mysql设置默认
     * 3.用dto创建CourseBase和CourseMarket对象
     * 4.插入到数据库中
     * 5.根据id从数据库中查找courseBase记录
     *    5.1 entity->dto(设置charge和price)
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public CourseBaseDTO createCourseBaseService(CourseBaseDTO dto) {
        // 1.校验参数
        //       *    基本参数在VO已经校验完毕
        //       *    判断教学模式是否合法(收费必须由价格)
        if(dto.getCharge().equals(CourseChargeEnum.CHARGE_YES.getCode()) && ObjectUtils.isEmpty(dto.getPrice())){
            throw new RuntimeException("收费课程价格不能为空");
        }
        // 2.设置默认参数
        //     audit_status 使用定义好的枚举类
        //     createDate mp帮我们设置
        //     status mysql存在定义值
        dto.setAuditStatus(AuditEnum.AUDIT_UNPAST_STATUS.getCode());

        //3.用dto创建CourseBase和CourseMarket对象并插入数据库
        CourseBase courseBase = CourseBaseConvert.INSTANCE.dto2entity(dto);


        boolean saveIsSuccess = this.save(courseBase);
        if(!saveIsSuccess){
            throw new RuntimeException("课程添加失败");
        }

        CourseMarket courseMarket = new CourseMarket();
        courseMarket.setCourseId(courseBase.getId());
        courseMarket.setCharge(dto.getCharge());
        if(!ObjectUtils.isEmpty(dto.getPrice())) {
            courseMarket.setPrice(dto.getPrice().floatValue());
        }
        boolean save = courseMarketService.save(courseMarket);
        if(!save){
            throw new RuntimeException("添加课程营销信息失败");
        }


        //5.根据id从数据库中查找courseBase记录
        CourseBase base = this.getById(courseBase.getId());
        CourseBaseDTO resultDTO = CourseBaseConvert.INSTANCE.entity2dto(base);

        //5.1 entity->dto(设置charge和price)
        resultDTO.setCharge(dto.getCharge());
        resultDTO.setPrice(dto.getPrice());

        return resultDTO;
    }

    /**
     * 1.获得companyId
     *
     * 2.获得courseBase，校验companyId
     *
     * 3.将查询到的内容封装到courseBaseDTO中并返回
     * @param courseBaseId
     * @return
     */
    @Override
    public CourseBaseDTO getCourseBaseById(Long courseBaseId,Long companyId) {
        //1.获得companyId

        //2.利用companyId和courseBaseId查询courseBase和courseMarket数据
        CourseBase courseBase = this.getById(courseBaseId);
        if(!courseBase.getCompanyId().equals(companyId)){
            ExceptionCast.cast(ContentErrorCode.E_120701);
        }
        CourseMarket courseMarket = courseMarketService.lambdaQuery().eq(CourseMarket::getCourseId, courseBaseId).one();

        //3.将查询到的内容封装到courseBaseDTO中并返回
        CourseBaseDTO dto = CourseBaseConvert.INSTANCE.entity2dto(courseBase);
        dto.setPrice(BigDecimal.valueOf(courseMarket.getPrice()));
        dto.setCharge(courseMarket.getCharge());
        return dto;
    }

    /**
     * 要求：课程基本信息的状态不能为'已提交' 、'审核通过'、'课程已发布' 状态
     *  //1.获得companyId,校验参数
     *      校验收费课程price>0,免费课程price=0
     *      检验courseBaseId不为null
     *      检验状态必须为
     *  //2.根据courseBaseId查询课程信息，对该课程状态进行校验
     *  //3.利用dto创建courseBase和courseMarket对象
     *      设置dto中
     *  //4.修改后查询courseBase表，将其封装成dto后返回
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public CourseBaseDTO modifyCourseBase(CourseBaseDTO dto) {
        //1.获得companyId
        Long companyId = SecurityUtil.getCompanyId();
        if(ObjectUtils.isEmpty(dto.getCourseBaseId())){
            ExceptionCast.cast(ContentErrorCode.E_120009);
        }

        //若该课程为收费课程，则需满足price>0
        if(dto.getCharge().equals(CourseChargeEnum.CHARGE_YES.getCode()) && (ObjectUtils.isEmpty(dto.getPrice()) || dto.getPrice().floatValue() <= 0)){
            ExceptionCast.cast(ContentErrorCode.E_120107);
        }
        //若该课程为免费课程，则需满足price=0
        if(dto.getCharge().equals(CourseChargeEnum.CHARGE_NO.getCode()) && (!ObjectUtils.isEmpty(dto.getPrice()) && dto.getPrice().floatValue() != 0)){
            ExceptionCast.cast(ContentErrorCode.E_120107);
        }

        //2.根据courseBaseId查询课程信息，验证companyId是否有效
        CourseBase courseBase = this.getById(dto.getCourseBaseId());
        if(!courseBase.getCompanyId().equals(companyId)){
            ExceptionCast.cast(ContentErrorCode.E_120017);
        }
        if(!courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_UNPAST_STATUS.getCode()) && !courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_DISPAST_STATUS.getCode())){
            ExceptionCast.cast(ContentErrorCode.E_120015);
        }

        //3.利用dto创建courseBase和courseMarket对象
        CourseBase basePO = CourseBaseConvert.INSTANCE.dto2entity(dto);

        //修改基本信息
        boolean save = this.updateById(basePO);
        if(!save){
            ExceptionCast.cast(ContentErrorCode.E_120017);
        }
        CourseMarket market = new CourseMarket();
        market.setCourseId(dto.getCourseBaseId());
        market.setPrice(dto.getPrice().floatValue());
        market.setCharge(dto.getCharge());
        //修改营销信息
        boolean success = courseMarketService.lambdaUpdate().
                eq(CourseMarket::getCourseId, dto.getCourseBaseId()).
                set(CourseMarket::getCharge, dto.getCharge()).
                set(CourseMarket::getPrice, dto.getPrice().floatValue()).
                update();
        if(!success){
            ExceptionCast.cast(ContentErrorCode.E_120107);
        }

        CourseBase base = this.getById(dto.getCourseBaseId());
        CourseBaseDTO baseDTO = CourseBaseConvert.INSTANCE.entity2dto(base);
        baseDTO.setCharge(dto.getCharge());
        baseDTO.setPrice(dto.getPrice());

        return baseDTO;
    }


    /**
     *  //1.获得companyId
     *  //2.根据courseBaseId得到courseBase对象
     *  //3.对couseBase进行校验
     *      //3.1 courseBase得companyId必须与步骤1的companyId一致
     *      //3.2 该课程的状态不能为已提交、审核通过和已发布
     *  //4.对该课程进行逻辑删除 设置status为0
     *  //5.返回逻辑删除是否成功
     * @param courseBaseId
     * @return
     */
    @Transactional
    @Override
    public Boolean removeCourseBase(Long courseBaseId) {
        if(ObjectUtils.isEmpty(courseBaseId) || courseBaseId <= 0){
            throw new RuntimeException("课程id不合法");
        }
        //1.获得companyId
        Long companyId = SecurityUtil.getCompanyId();

        //2.根据courseBaseId得到courseBase对象
        CourseBase courseBase = this.getById(courseBaseId);

        //3.对couseBase进行校验
           //3.1 courseBase得companyId必须与步骤1的companyId一致
        if(!courseBase.getCompanyId().equals(companyId)){
            ExceptionCast.cast(ContentErrorCode.E_120017);
        }
           //3.2 该课程的状态不能为已提交、审核通过和已发布
        if(courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_COMMIT_STATUS.getCode()) ||
                courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_PASTED_STATUS.getCode()) ||
                courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_PUBLISHED_STATUS.getCode())){
           ExceptionCast.cast(ContentErrorCode.E_120014);
        }
        //4.对该课程进行逻辑删除 设置status为0
        boolean remove = this.removeById(courseBaseId);


        //5.返回逻辑删除是否成功
        return remove;
    }

    /**
     * 1.校验业务数据
     *      courseBase是否存在
     *      companyId是否一致
     *      审核状态必须为未提交
     * 2.修改审核状态
     * @param courseBaseId
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public boolean commitCourse(Long courseBaseId, Long companyId) {
        //1.校验courseBase以及companyId
        CourseBaseDTO baseDTO = getCourseBaseById(courseBaseId,companyId);

        //2.校验审核状态，只能对审核未提交和未通过的课程进行修改
        String auditStatus = baseDTO.getAuditStatus();
        if(!auditStatus.equals(CourseAuditEnum.AUDIT_UNPAST_STATUS.getCode()) && ! auditStatus.equals(CourseAuditEnum.AUDIT_DISPAST_STATUS.getCode())){
            ExceptionCast.cast(ContentErrorCode.E_120014);
        }

        //3.修改课程审核状态
        boolean update = this.lambdaUpdate().eq(CourseBase::getId, baseDTO.getCourseBaseId()).set(CourseBase::getAuditStatus, CourseAuditEnum.AUDIT_COMMIT_STATUS.getCode()).update();
        if(!update){
            ExceptionCast.cast(ContentErrorCode.E_120017);
        }
        return true;
    }
}
