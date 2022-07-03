package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.content.entity.CourseBase;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-02T22:46:31+0800",
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
        courseBaseDTO.setStatus( entity.getStatus() );
        courseBaseDTO.setCoursePubId( entity.getCoursePubId() );

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
}
