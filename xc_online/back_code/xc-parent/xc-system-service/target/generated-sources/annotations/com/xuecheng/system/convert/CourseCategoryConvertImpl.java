package com.xuecheng.system.convert;

import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.system.entity.CourseCategory;
import com.xuecheng.system.entity.ext.CourseCategoryNode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T11:44:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class CourseCategoryConvertImpl implements CourseCategoryConvert {

    @Override
    public CourseCategoryDTO node2dto(CourseCategoryNode node) {
        if ( node == null ) {
            return null;
        }

        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();

        List<CourseCategoryNode> list = node.getChildrenTreeNodes();
        if ( list != null ) {
            courseCategoryDTO.setCategoryTreeNodes( new ArrayList( list ) );
        }
        courseCategoryDTO.setCourseCategoryId( node.getId() );
        courseCategoryDTO.setName( node.getName() );
        courseCategoryDTO.setLabel( node.getLabel() );
        courseCategoryDTO.setParentid( node.getParentid() );
        courseCategoryDTO.setIsShow( node.getIsShow() );
        courseCategoryDTO.setOrderby( node.getOrderby() );
        courseCategoryDTO.setIsLeaf( node.getIsLeaf() );

        return courseCategoryDTO;
    }

    @Override
    public CourseCategoryDTO entity2dto(CourseCategory courseCategory) {
        if ( courseCategory == null ) {
            return null;
        }

        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();

        courseCategoryDTO.setCourseCategoryId( courseCategory.getId() );
        courseCategoryDTO.setName( courseCategory.getName() );
        courseCategoryDTO.setLabel( courseCategory.getLabel() );
        courseCategoryDTO.setParentid( courseCategory.getParentid() );
        courseCategoryDTO.setIsShow( courseCategory.getIsShow() );
        courseCategoryDTO.setOrderby( courseCategory.getOrderby() );
        courseCategoryDTO.setIsLeaf( courseCategory.getIsLeaf() );

        return courseCategoryDTO;
    }

    @Override
    public List<CourseCategoryDTO> nodes2dtos(List<CourseCategoryNode> nodes) {
        if ( nodes == null ) {
            return null;
        }

        List<CourseCategoryDTO> list = new ArrayList<CourseCategoryDTO>( nodes.size() );
        for ( CourseCategoryNode courseCategoryNode : nodes ) {
            list.add( node2dto( courseCategoryNode ) );
        }

        return list;
    }
}
