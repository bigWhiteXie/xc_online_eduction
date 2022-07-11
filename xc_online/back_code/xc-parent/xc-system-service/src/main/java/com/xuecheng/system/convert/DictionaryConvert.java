package com.xuecheng.system.convert;

import com.xuecheng.api.system.model.dto.DictionaryDTO;
import com.xuecheng.system.entity.Dictionary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@Mapper
public interface DictionaryConvert {

    DictionaryConvert INSTANCE = Mappers.getMapper(DictionaryConvert.class);

    @Mapping(source = "id",target = "dicId")
    DictionaryDTO entity2dto(Dictionary dictionary);

    List<DictionaryDTO> entitys2dtos(List<Dictionary> dictionarys);
}
