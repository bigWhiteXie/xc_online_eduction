package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.vo.CourseBaseVO;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.content.entity.CourseBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseBaseConvert {
    CourseBaseConvert INSTANCE = Mappers.getMapper(CourseBaseConvert.class);

    @Mapping(source = "id",target = "courseBaseId")
    CourseBaseDTO entity2dto(CourseBase entity);

    @Mappings({
            @Mapping(source = "courseBaseId",target = "id"),
    })
    CourseBase dto2entity(CourseBaseDTO dto);

    CourseBaseDTO vo2dto(CourseBaseVO courseBaseVO);




    List<CourseBaseDTO> entities2dtos(List<CourseBase> list);

    List<CourseBase> dtos2entities(List<CourseBaseDTO> list);


}
