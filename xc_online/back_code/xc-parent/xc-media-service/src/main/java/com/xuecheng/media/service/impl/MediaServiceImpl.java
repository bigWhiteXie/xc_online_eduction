package com.xuecheng.media.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.qo.QueryMediaModel;
import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.common.enums.common.AuditEnum;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.common.util.StringUtil;
import com.xuecheng.media.common.constant.MediaErrorCode;
import com.xuecheng.media.common.utils.AliyunVODUtil;
import com.xuecheng.media.controller.AliYunController;
import com.xuecheng.media.controller.MediaAuditController;
import com.xuecheng.media.convert.MediaConvert;
import com.xuecheng.media.entity.Media;
import com.xuecheng.media.mapper.MediaMapper;
import com.xuecheng.media.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 媒资信息 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media> implements MediaService {

    /**
     * 1.检查关键数据
     *      重要字段不能为空
     * 2.校验业务数据
     *       无需校验
     * 3.将dto转为po插入数据库
     *      设置auditStatus为未审核
     * 4.查询数据转为dto返回
     * @param dto
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public MediaDTO saveMediaInfo(MediaDTO dto, Long companyId) {
        //1.将dto转为po
        Media media = MediaConvert.INSTANCE.dto2po(dto);

        //2.为po设置额外信息：companyId和auditStatus
        media.setCompanyId(companyId);
        media.setAuditStatus(AuditEnum.AUDIT_UNPAST_STATUS.getCode());
        boolean save = this.save(media);
        //插入失败则抛出异常
        if(!save){
            ExceptionCast.cast(MediaErrorCode.E_140001);
        }

        //3.从数据库中查询新插入的数据
        Media byId = this.getById(media.getId());

        MediaDTO mediaDTO = MediaConvert.INSTANCE.po2dto(byId);


        return mediaDTO;
    }

    @Override
    public PageVO queryMediaList(PageRequestParams params, QueryMediaModel model, Long companyId) {
        // 1.判断关键数据
        // 分页数据的合法性
        if (params.getPageNo() < 1) {
            params.setPageNo(PageRequestParams.DEFAULT_PAGE_NUM);
        }

        if (params.getPageSize() < 1) {
            params.setPageSize(PageRequestParams.DEFAULT_PAGE_SIZE);
        }


        // 2.构建分页对象
        Page<Media> basePage = new Page<>(params.getPageNo(),params.getPageSize());

        // 3.构建查询条件对象
        LambdaQueryWrapper<Media> queryWrapper = new LambdaQueryWrapper<>();

        // 课程名称     like   完整版
        // 课程审核状态  eq
        // String courseName = model.getCourseName();
        // if (StringUtil.isNotBlank(courseName)) {
        //     queryWrapper.like(CourseBase::getName, courseName);
        // }
        //
        // String auditStatus = model.getAuditStatus();
        // if (StringUtil.isNotBlank(auditStatus)) {
        //     queryWrapper.eq(CourseBase::getAuditStatus, auditStatus);
        // }

        // 简写版
        queryWrapper.like(StringUtil.isNotBlank(model.getFilename()),Media::getFilename, model.getFilename());
        queryWrapper.eq(StringUtil.isNotBlank(model.getType()),Media::getType, model.getType());
        queryWrapper.eq(StringUtil.isNotBlank(model.getAuditStatus()),Media::getAuditStatus, model.getAuditStatus());
        //教学机构只能查询本机构的课程信息
        if (!(ObjectUtils.nullSafeEquals(companyId, MediaAuditController.OPERATION_FLAG))) {
            queryWrapper.eq(Media::getCompanyId, companyId);
        }



        // 4.查询数据内容获得结果
        Page<Media> page = this.page(basePage, queryWrapper);

        List<Media> records = page.getRecords();
        long total = page.getTotal();


        List<MediaDTO> dtos = Collections.emptyList();

        if (!(CollectionUtils.isEmpty(records))) {
            dtos = MediaConvert.INSTANCE.pos2dtos(records);
        }

        // 5.构建返回结果数据-PageVo
        PageVO pageVO = new PageVO(dtos,total,params.getPageNo(),params.getPageSize());

        return pageVO;
    }

    @Override
    public RestResponse<MediaDTO> getMediaById4s(Long mediaId) {
        Media media = this.getById(mediaId);
        MediaDTO dto = MediaConvert.INSTANCE.po2dto(media);
        RestResponse<MediaDTO> response = RestResponse.success(dto);
        return response;
    }

    /**
     * 1.关键数据校验
     *      mediaId不为null
     * 2. 业务数据校验
     *      mediaId对应的media不为null
     *      companyId若存在则校验，若不存在则说明是运营方
     * 3. 得到资源key后向阿里云发起请求获得视频url
     *
     * @param mediaId
     * @param companyId
     * @return
     */
    @Override
    public String getVODUrl(Long mediaId, Long companyId) {

        //1.关键数据校验
        //        mediaId不为null
        if(ObjectUtils.isEmpty(mediaId)){
            ExceptionCast.cast(MediaErrorCode.E_140005);
        }

        //2. 业务数据校验
        //        mediaId对应的media不为null
        Media media = this.getById(mediaId);
        if(media == null){
            ExceptionCast.cast(MediaErrorCode.E_140005);
        }

        //companyId若存在则校验，若不存在则说明是运营方
        if (!(ObjectUtils.isEmpty(companyId))) {
            if (!(ObjectUtils.nullSafeEquals(media.getCompanyId(), companyId))) {
                ExceptionCast.cast(CommonErrorCode.E_100108);
            }
        }
        //3.判断media的url是否存在
        String playUrl = media.getUrl();
        if(ObjectUtils.isEmpty(playUrl)) { //若不存在则向阿里云发送请求，得到url
            playUrl = getPlayUrl(media.getFileId());
        }
        return playUrl;
    }

    /**
     * 1.判断关键数据
     *
     * 2.校验业务数据
     *      media不能为null
     *      auditStatus只能为未审核和被拒绝
     *      companyId必须一致
     * 3.逻辑删除该记录
     * @param mediaId
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public Boolean removeMediaById(Long mediaId, Long companyId) {
        //1.判断关键数据
        if(ObjectUtils.isEmpty(mediaId) || ObjectUtils.isEmpty(companyId)){
            ExceptionCast.cast(MediaErrorCode.E_140008);
        }
        //2.校验业务数据
        //        media不能为null
        Media media = this.getById(mediaId);
        //auditStatus只能为未审核和被拒绝
        if(!media.getAuditStatus().equals(AuditEnum.AUDIT_UNPAST_STATUS.getCode()) && !media.getAuditStatus().equals(AuditEnum.AUDIT_DISPAST_STATUS.getCode())){
            ExceptionCast.cast(MediaErrorCode.E_140009);
        }
        //        companyId必须一致
        if(!media.getCompanyId().equals(companyId)){
            ExceptionCast.cast(MediaErrorCode.E_140002);
        }
        //3.逻辑删除该记录
        boolean remove = this.removeById(mediaId);
        if(!remove){
            ExceptionCast.cast(MediaErrorCode.E_140002);
        }

        return remove;
    }

    private String getPlayUrl(String videoId) {

        String playUrl = "";
        try {

            DefaultAcsClient client =
                    AliyunVODUtil.initVodClient(AliYunController.region,AliYunController.accessKey,AliYunController.secretKey);

            GetPlayInfoResponse response = AliyunVODUtil.getPlayInfo(client,videoId);

            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

            if (!(ObjectUtils.isEmpty(playInfoList))) {
                GetPlayInfoResponse.PlayInfo playInfo = playInfoList.get(0);
                playUrl = playInfo.getPlayURL();
            }

        } catch (Exception e) {
            ExceptionCast.cast(MediaErrorCode.E_140014);
        }
        return playUrl;
    }
}

