package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.vo.CourseBaseVO;
import com.xuecheng.content.entity.CourseBase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T11:33:12+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class CourseBaseConvertImpl implements CourseBaseConvert {

    @Override
    public CourseBaseDTO entity2dto(CourseBase entity) {
        if ( entity == null ) {
            return null;
        }

        CourseBaseDTO courseBaseDTO = new CourseBaseDTO();

        courseBaseDTO.setCourseBaseId( entity.getId() );
        courseBaseDTO.setCompanyId( entity.getCompanyId() );
        courseBaseDTO.setCompanyName( entity.getCompanyName() );
        courseBaseDTO.setName( entity.getName() );
        courseBaseDTO.setUsers( entity.getUsers() );
        courseBaseDTO.setTags( entity.getTags() );
        courseBaseDTO.setMt( entity.getMt() );
        courseBaseDTO.setMtName( entity.getMtName() );
        courseBaseDTO.setSt( entity.getSt() );
        courseBaseDTO.setStName( entity.getStName() );
        courseBaseDTO.setGrade( entity.getGrade() );
        courseBaseDTO.setTeachmode( entity.getTeachmode() );
        courseBaseDTO.setDescription( entity.getDescription() );
        courseBaseDTO.setPic( entity.getPic() );
        courseBaseDTO.setCreateDate( entity.getCreateDate() );
        courseBaseDTO.setAuditStatus( entity.getAuditStatus() );
        courseBaseDTO.setAuditMind( entity.getAuditMind() );
        courseBaseDTO.setAuditNums( entity.getAuditNums() );
        if ( entity.getAuditDate() != null ) {
            courseBaseDTO.setAuditDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getAuditDate() ) );
        }
        if ( entity.getChangeDate() != null ) {
            courseBaseDTO.setChangeDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getChangeDate() ) );
        }
        courseBaseDTO.setStatus( entity.getStatus() );
        courseBaseDTO.setCoursePubId( entity.getCoursePubId() );

        return courseBaseDTO;
    }

    @Override
    public CourseBase dto2entity(CourseBaseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CourseBase courseBase = new CourseBase();

        courseBase.setId( dto.getCourseBaseId() );
        courseBase.setCompanyId( dto.getCompanyId() );
        courseBase.setCompanyName( dto.getCompanyName() );
        courseBase.setName( dto.getName() );
        courseBase.setUsers( dto.getUsers() );
        courseBase.setTags( dto.getTags() );
        courseBase.setMt( dto.getMt() );
        courseBase.setMtName( dto.getMtName() );
        courseBase.setSt( dto.getSt() );
        courseBase.setStName( dto.getStName() );
        courseBase.setGrade( dto.getGrade() );
        courseBase.setTeachmode( dto.getTeachmode() );
        courseBase.setDescription( dto.getDescription() );
        courseBase.setPic( dto.getPic() );
        courseBase.setCreateDate( dto.getCreateDate() );
        if ( dto.getChangeDate() != null ) {
            courseBase.setChangeDate( LocalDateTime.parse( dto.getChangeDate() ) );
        }
        courseBase.setAuditStatus( dto.getAuditStatus() );
        courseBase.setAuditMind( dto.getAuditMind() );
        courseBase.setAuditNums( dto.getAuditNums() );
        if ( dto.getAuditDate() != null ) {
            courseBase.setAuditDate( LocalDateTime.parse( dto.getAuditDate() ) );
        }
        courseBase.setStatus( dto.getStatus() );
        courseBase.setCoursePubId( dto.getCoursePubId() );

        return courseBase;
    }

    @Override
    public CourseBaseDTO vo2dto(CourseBaseVO courseBaseVO) {
        if ( courseBaseVO == null ) {
            return null;
        }

        CourseBaseDTO courseBaseDTO = new CourseBaseDTO();

        courseBaseDTO.setCourseBaseId( courseBaseVO.getCourseBaseId() );
        courseBaseDTO.setName( courseBaseVO.getName() );
        courseBaseDTO.setUsers( courseBaseVO.getUsers() );
        courseBaseDTO.setTags( courseBaseVO.getTags() );
        courseBaseDTO.setMt( courseBaseVO.getMt() );
        courseBaseDTO.setSt( courseBaseVO.getSt() );
        courseBaseDTO.setGrade( courseBaseVO.getGrade() );
        courseBaseDTO.setTeachmode( courseBaseVO.getTeachmode() );
        courseBaseDTO.setDescription( courseBaseVO.getDescription() );
        courseBaseDTO.setPic( courseBaseVO.getPic() );
        courseBaseDTO.setCharge( courseBaseVO.getCharge() );
        courseBaseDTO.setPrice( courseBaseVO.getPrice() );

        return courseBaseDTO;
    }

    @Override
    public List<CourseBaseDTO> entities2dtos(List<CourseBase> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseBaseDTO> list1 = new ArrayList<CourseBaseDTO>( list.size() );
        for ( CourseBase courseBase : list ) {
            list1.add( entity2dto( courseBase ) );
        }

        return list1;
    }

    @Override
    public List<CourseBase> dtos2entities(List<CourseBaseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CourseBase> list1 = new ArrayList<CourseBase>( list.size() );
        for ( CourseBaseDTO courseBaseDTO : list ) {
            list1.add( dto2entity( courseBaseDTO ) );
        }

        return list1;
    }
}
