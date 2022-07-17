package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.qo.QueryMediaModel;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.media.entity.Media;

/**
 * <p>
 * 媒资信息 服务类
 * </p>
 *
 * @author itcast
 * @since 2022-07-14
 */
public interface MediaService extends IService<Media> {

    MediaDTO saveMediaInfo(MediaDTO dto, Long companyId);

    public PageVO queryMediaList(PageRequestParams params, QueryMediaModel model, Long companyId);

    RestResponse<MediaDTO> getMediaById4s(Long mediaId);

    String getVODUrl(Long mediaId, Long companyId);

    Boolean removeMediaById(Long mediaId, Long companyId);
}
