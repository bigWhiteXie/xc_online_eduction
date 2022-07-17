package com.xuecheng.api.media;

import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.qo.QueryMediaModel;
import com.xuecheng.api.media.model.vo.MediaVO;
import com.xuecheng.common.constant.XcFeignServiceNameList;
import com.xuecheng.common.domain.page.PageRequestParams;
import com.xuecheng.common.domain.page.PageVO;
import com.xuecheng.common.domain.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Api(value = "媒资管理Api",tags = "媒资管理Api",description = "对课程录屏进行管理")
@FeignClient(XcFeignServiceNameList.XC_MEDIA_SERVICE)
public interface MediaApi {
    String prefix = "media/";

    @PostMapping(prefix + "media")
    @ApiOperation(value = "保存媒资信息")
    public MediaDTO saveMediaInfo(MediaVO mediaVO);

    @PostMapping(prefix+ "media/list")
    @ApiOperation(value = "查询媒体资源列表")
    PageVO queryMediaList(PageRequestParams params, QueryMediaModel model);


    @GetMapping(prefix + "l/media/{mediaId}")
    @ApiOperation(value = "通过id获得媒体(针对微服务)")
    public RestResponse<MediaDTO> getMediaById4s(@PathVariable Long mediaId) ;

    @GetMapping(prefix + "/media/preview/{mediaId}")
    @ApiOperation(value = "预览媒体资源")
    public String previewMedia(@PathVariable Long mediaId);

    @DeleteMapping(prefix + "media/{mediaId}")
    @ApiOperation(value = "删除媒体资源")
    public Boolean removeMediaById(@PathVariable Long mediaId);
}
