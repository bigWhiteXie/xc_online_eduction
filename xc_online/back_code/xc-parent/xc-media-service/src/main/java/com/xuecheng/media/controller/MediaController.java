package com.xuecheng.media.controller;

import com.xuecheng.api.media.MediaApi;
import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.qo.QueryMediaModel;
import com.xuecheng.api.media.model.vo.MediaVO;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.common.util.SecurityUtil;
import com.xuecheng.media.convert.MediaConvert;
import com.xuecheng.media.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 媒资信息 前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
public class MediaController implements MediaApi {

    @Autowired
    private MediaService  mediaService;


    @PostMapping("media")
    @Override
    public MediaDTO saveMediaInfo(@RequestBody MediaVO mediaVO) {
        //1.获取公司id
        Long companyId = SecurityUtil.getCompanyId();

        //2.将vo转成dto
        MediaDTO dto = MediaConvert.INSTANCE.vo2dto(mediaVO);
        return mediaService.saveMediaInfo(dto,companyId);
    }

    @PostMapping("media/list")
    @Override
    public PageVO queryMediaList(PageRequestParams params, QueryMediaModel model) {
        Long companyId = SecurityUtil.getCompanyId();
        PageVO pageVO = mediaService.queryMediaList(params, model, companyId);
        return pageVO;
    }

    @GetMapping("l/media/{mediaId}")
    @Override
    public RestResponse<MediaDTO> getMediaById4s(Long mediaId) {
        return mediaService.getMediaById4s(mediaId);
    }

    @GetMapping("/media/preview/{mediaId}")
    public String previewMedia(@PathVariable Long mediaId) {

        Long companyId = SecurityUtil.getCompanyId();

        String vodUrl = mediaService.getVODUrl(mediaId,companyId);

        return vodUrl;
    }

    @DeleteMapping("media/{mediaId}")
    @Override
    public Boolean removeMediaById(@PathVariable Long mediaId) {
        Long companyId = SecurityUtil.getCompanyId();
        return mediaService.removeMediaById(mediaId,companyId);
    }





}
