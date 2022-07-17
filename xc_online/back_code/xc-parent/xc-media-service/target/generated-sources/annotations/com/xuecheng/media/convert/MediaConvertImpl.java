package com.xuecheng.media.convert;

import com.xuecheng.api.media.model.dto.MediaDTO;
import com.xuecheng.api.media.model.vo.MediaVO;
import com.xuecheng.media.entity.Media;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-16T18:38:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class MediaConvertImpl implements MediaConvert {

    @Override
    public MediaDTO vo2dto(MediaVO vo) {
        if ( vo == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setFilename( vo.getFilename() );
        mediaDTO.setType( vo.getType() );
        mediaDTO.setTags( vo.getTags() );
        mediaDTO.setBucket( vo.getBucket() );
        mediaDTO.setFileId( vo.getFileId() );
        mediaDTO.setUrl( vo.getUrl() );
        mediaDTO.setTimelength( vo.getTimelength() );
        mediaDTO.setRemark( vo.getRemark() );

        return mediaDTO;
    }

    @Override
    public Media dto2po(MediaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( dto.getId() );
        media.setCompanyId( dto.getCompanyId() );
        media.setCompanyName( dto.getCompanyName() );
        media.setFilename( dto.getFilename() );
        media.setType( dto.getType() );
        media.setTags( dto.getTags() );
        media.setBucket( dto.getBucket() );
        media.setFileId( dto.getFileId() );
        media.setUrl( dto.getUrl() );
        media.setTimelength( dto.getTimelength() );
        media.setUsername( dto.getUsername() );
        if ( dto.getCreateDate() != null ) {
            media.setCreateDate( LocalDateTime.parse( dto.getCreateDate() ) );
        }
        media.setChangeDate( dto.getChangeDate() );
        media.setStatus( dto.getStatus() );
        media.setRemark( dto.getRemark() );
        media.setAuditStatus( dto.getAuditStatus() );
        media.setAuditMind( dto.getAuditMind() );

        return media;
    }

    @Override
    public MediaDTO po2dto(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setCompanyId( media.getCompanyId() );
        mediaDTO.setCompanyName( media.getCompanyName() );
        mediaDTO.setFilename( media.getFilename() );
        mediaDTO.setType( media.getType() );
        mediaDTO.setTags( media.getTags() );
        mediaDTO.setBucket( media.getBucket() );
        mediaDTO.setFileId( media.getFileId() );
        mediaDTO.setUrl( media.getUrl() );
        mediaDTO.setTimelength( media.getTimelength() );
        mediaDTO.setUsername( media.getUsername() );
        if ( media.getCreateDate() != null ) {
            mediaDTO.setCreateDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( media.getCreateDate() ) );
        }
        mediaDTO.setChangeDate( media.getChangeDate() );
        mediaDTO.setStatus( media.getStatus() );
        mediaDTO.setRemark( media.getRemark() );
        mediaDTO.setAuditStatus( media.getAuditStatus() );
        mediaDTO.setAuditMind( media.getAuditMind() );

        return mediaDTO;
    }

    @Override
    public List<MediaDTO> pos2dtos(List<Media> list) {
        if ( list == null ) {
            return null;
        }

        List<MediaDTO> list1 = new ArrayList<MediaDTO>( list.size() );
        for ( Media media : list ) {
            list1.add( po2dto( media ) );
        }

        return list1;
    }
}
