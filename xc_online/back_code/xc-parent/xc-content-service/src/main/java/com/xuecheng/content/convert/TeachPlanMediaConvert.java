package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.TeachplanMediaDTO;
import com.xuecheng.content.entity.TeachplanMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeachPlanMediaConvert {
    TeachPlanMediaConvert INSTANCE = Mappers.getMapper(TeachPlanMediaConvert.class);




    List<TeachplanMediaDTO> entitys2dtos(List<TeachplanMedia> teachplanMedias);
}
