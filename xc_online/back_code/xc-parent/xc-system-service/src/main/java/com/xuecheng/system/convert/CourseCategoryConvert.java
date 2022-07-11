package com.xuecheng.system.convert;

import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.system.entity.CourseCategory;
import com.xuecheng.system.entity.ext.CourseCategoryNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@Mapper
public interface CourseCategoryConvert {

    CourseCategoryConvert INSTANCE = Mappers.getMapper(CourseCategoryConvert.class);

    @Mappings({
            @Mapping(source = "id",target = "courseCategoryId"),
            @Mapping(source = "childrenTreeNodes",target = "categoryTreeNodes")
    })
    CourseCategoryDTO node2dto(CourseCategoryNode node);


    @Mapping(source = "id",target = "courseCategoryId")
    CourseCategoryDTO entity2dto(CourseCategory courseCategory);

    List<CourseCategoryDTO> nodes2dtos(List<CourseCategoryNode> nodes);

}
