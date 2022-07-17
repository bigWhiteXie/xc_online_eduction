package com.xuecheng.media.convert;


import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.vo.MediaVO;
import com.xuecheng.media.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MediaConvert {
    MediaConvert INSTANCE = Mappers.getMapper(MediaConvert.class);


    MediaDTO vo2dto(MediaVO vo);

    Media dto2po(MediaDTO dto);

    MediaDTO po2dto(Media media);

    List<MediaDTO> pos2dtos(List<Media> list);






}
