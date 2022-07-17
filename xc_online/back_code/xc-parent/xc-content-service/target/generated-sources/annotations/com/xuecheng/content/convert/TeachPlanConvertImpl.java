package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.api.content.model.dto.TeachplanMediaDTO;
import com.xuecheng.api.content.model.vo.TeachplanVO;
import com.xuecheng.content.entity.Teachplan;
import com.xuecheng.content.entity.TeachplanMedia;
import com.xuecheng.content.entity.ex.TeachPlanTreeNode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T11:29:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class TeachPlanConvertImpl implements TeachPlanConvert {

    @Override
    public TeachplanDTO node2dto(TeachPlanTreeNode node) {
        if ( node == null ) {
            return null;
        }

        TeachplanDTO teachplanDTO = new TeachplanDTO();

        teachplanDTO.setId( node.getId() );
        teachplanDTO.setPname( node.getPname() );
        teachplanDTO.setParentid( node.getParentid() );
        teachplanDTO.setGrade( node.getGrade() );
        teachplanDTO.setMediaType( node.getMediaType() );
        teachplanDTO.setStartTime( node.getStartTime() );
        teachplanDTO.setEndTime( node.getEndTime() );
        teachplanDTO.setDescription( node.getDescription() );
        teachplanDTO.setTimelength( node.getTimelength() );
        teachplanDTO.setOrderby( node.getOrderby() );
        teachplanDTO.setCourseId( node.getCourseId() );
        teachplanDTO.setCoursePubId( node.getCoursePubId() );
        teachplanDTO.setStatus( node.getStatus() );
        teachplanDTO.setIsPreview( node.getIsPreview() );
        teachplanDTO.setCreateDate( node.getCreateDate() );
        teachplanDTO.setChangeDate( node.getChangeDate() );
        teachplanDTO.setTeachPlanTreeNodes( nodes2dtos( node.getTeachPlanTreeNodes() ) );
        teachplanDTO.setTeachplanMedia( entity2dto( node.getTeachplanMedia() ) );

        return teachplanDTO;
    }

    @Override
    public TeachplanDTO vo2dto(TeachplanVO vo) {
        if ( vo == null ) {
            return null;
        }

        TeachplanDTO teachplanDTO = new TeachplanDTO();

        teachplanDTO.setId( vo.getTeachPlanId() );
        teachplanDTO.setPname( vo.getPname() );
        teachplanDTO.setParentid( vo.getParentid() );
        teachplanDTO.setGrade( vo.getGrade() );
        teachplanDTO.setMediaType( vo.getMediaType() );
        teachplanDTO.setStartTime( vo.getStartTime() );
        teachplanDTO.setEndTime( vo.getEndTime() );
        teachplanDTO.setOrderby( vo.getOrderby() );
        teachplanDTO.setCourseId( vo.getCourseId() );
        teachplanDTO.setIsPreview( vo.getIsPreview() );

        return teachplanDTO;
    }

    @Override
    public TeachplanMediaDTO entity2dto(TeachplanMedia teachplanMedia) {
        if ( teachplanMedia == null ) {
            return null;
        }

        TeachplanMediaDTO teachplanMediaDTO = new TeachplanMediaDTO();

        teachplanMediaDTO.setTeachplanMediaId( teachplanMedia.getId() );
        teachplanMediaDTO.setMediaId( teachplanMedia.getMediaId() );
        teachplanMediaDTO.setTeachplanId( teachplanMedia.getTeachplanId() );
        teachplanMediaDTO.setCourseId( teachplanMedia.getCourseId() );
        teachplanMediaDTO.setCoursePubId( teachplanMedia.getCoursePubId() );
        teachplanMediaDTO.setMediaFilename( teachplanMedia.getMediaFilename() );

        return teachplanMediaDTO;
    }

    @Override
    public List<TeachplanDTO> nodes2dtos(List<TeachPlanTreeNode> node) {
        if ( node == null ) {
            return null;
        }

        List<TeachplanDTO> list = new ArrayList<TeachplanDTO>( node.size() );
        for ( TeachPlanTreeNode teachPlanTreeNode : node ) {
            list.add( node2dto( teachPlanTreeNode ) );
        }

        return list;
    }

    @Override
    public Teachplan dto2po(TeachplanDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Teachplan teachplan = new Teachplan();

        teachplan.setId( dto.getId() );
        teachplan.setPname( dto.getPname() );
        teachplan.setParentid( dto.getParentid() );
        teachplan.setGrade( dto.getGrade() );
        teachplan.setMediaType( dto.getMediaType() );
        teachplan.setStartTime( dto.getStartTime() );
        teachplan.setEndTime( dto.getEndTime() );
        teachplan.setDescription( dto.getDescription() );
        teachplan.setTimelength( dto.getTimelength() );
        teachplan.setOrderby( dto.getOrderby() );
        teachplan.setCourseId( dto.getCourseId() );
        teachplan.setCoursePubId( dto.getCoursePubId() );
        teachplan.setStatus( dto.getStatus() );
        teachplan.setIsPreview( dto.getIsPreview() );
        teachplan.setCreateDate( dto.getCreateDate() );
        teachplan.setChangeDate( dto.getChangeDate() );

        return teachplan;
    }

    @Override
    public TeachplanDTO po2dto(Teachplan teachplan) {
        if ( teachplan == null ) {
            return null;
        }

        TeachplanDTO teachplanDTO = new TeachplanDTO();

        teachplanDTO.setId( teachplan.getId() );
        teachplanDTO.setPname( teachplan.getPname() );
        teachplanDTO.setParentid( teachplan.getParentid() );
        teachplanDTO.setGrade( teachplan.getGrade() );
        teachplanDTO.setMediaType( teachplan.getMediaType() );
        teachplanDTO.setStartTime( teachplan.getStartTime() );
        teachplanDTO.setEndTime( teachplan.getEndTime() );
        teachplanDTO.setDescription( teachplan.getDescription() );
        teachplanDTO.setTimelength( teachplan.getTimelength() );
        teachplanDTO.setOrderby( teachplan.getOrderby() );
        teachplanDTO.setCourseId( teachplan.getCourseId() );
        teachplanDTO.setCoursePubId( teachplan.getCoursePubId() );
        teachplanDTO.setStatus( teachplan.getStatus() );
        teachplanDTO.setIsPreview( teachplan.getIsPreview() );
        teachplanDTO.setCreateDate( teachplan.getCreateDate() );
        teachplanDTO.setChangeDate( teachplan.getChangeDate() );

        return teachplanDTO;
    }
}
