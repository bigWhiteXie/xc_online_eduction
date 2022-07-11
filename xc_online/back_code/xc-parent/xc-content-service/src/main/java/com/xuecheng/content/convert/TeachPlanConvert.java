package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.api.content.model.vo.TeachplanVO;
import com.xuecheng.content.entity.Teachplan;
import com.xuecheng.content.entity.ex.TeachPlanTreeNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TeachPlanConvert {

    TeachPlanConvert INSTANCE = Mappers.getMapper(TeachPlanConvert.class);

    TeachplanDTO node2dto(TeachPlanTreeNode node);

    @Mapping(source = "teachPlanId", target = "id")
    TeachplanDTO vo2dto(TeachplanVO vo);

    List<TeachplanDTO> nodes2dtos(List<TeachPlanTreeNode> node);

    Teachplan dto2po(TeachplanDTO dto);


    TeachplanDTO po2dto(Teachplan teachplan);
}
