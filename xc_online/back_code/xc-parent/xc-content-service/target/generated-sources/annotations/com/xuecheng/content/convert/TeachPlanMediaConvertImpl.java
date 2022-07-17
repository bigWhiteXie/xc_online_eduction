package com.xuecheng.content.convert;

import com.xuecheng.api.content.model.dto.TeachplanMediaDTO;
import com.xuecheng.content.entity.TeachplanMedia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T11:29:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class TeachPlanMediaConvertImpl implements TeachPlanMediaConvert {

    @Override
    public List<TeachplanMediaDTO> entitys2dtos(List<TeachplanMedia> teachplanMedias) {
        if ( teachplanMedias == null ) {
            return null;
        }

        List<TeachplanMediaDTO> list = new ArrayList<TeachplanMediaDTO>( teachplanMedias.size() );
        for ( TeachplanMedia teachplanMedia : teachplanMedias ) {
            list.add( teachplanMediaToTeachplanMediaDTO( teachplanMedia ) );
        }

        return list;
    }

    protected TeachplanMediaDTO teachplanMediaToTeachplanMediaDTO(TeachplanMedia teachplanMedia) {
        if ( teachplanMedia == null ) {
            return null;
        }

        TeachplanMediaDTO teachplanMediaDTO = new TeachplanMediaDTO();

        teachplanMediaDTO.setMediaId( teachplanMedia.getMediaId() );
        teachplanMediaDTO.setTeachplanId( teachplanMedia.getTeachplanId() );
        teachplanMediaDTO.setCourseId( teachplanMedia.getCourseId() );
        teachplanMediaDTO.setCoursePubId( teachplanMedia.getCoursePubId() );
        teachplanMediaDTO.setMediaFilename( teachplanMedia.getMediaFilename() );

        return teachplanMediaDTO;
    }
}
