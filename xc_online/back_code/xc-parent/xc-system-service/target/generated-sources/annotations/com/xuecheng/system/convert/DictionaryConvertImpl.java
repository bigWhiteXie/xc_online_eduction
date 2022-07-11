package com.xuecheng.system.convert;

import com.xuecheng.api.system.model.dto.DictionaryDTO;
import com.xuecheng.system.entity.Dictionary;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T11:33:09+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class DictionaryConvertImpl implements DictionaryConvert {

    @Override
    public DictionaryDTO entity2dto(Dictionary dictionary) {
        if ( dictionary == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setDicId( dictionary.getId() );
        dictionaryDTO.setName( dictionary.getName() );
        dictionaryDTO.setCode( dictionary.getCode() );
        dictionaryDTO.setItemValues( dictionary.getItemValues() );

        return dictionaryDTO;
    }

    @Override
    public List<DictionaryDTO> entitys2dtos(List<Dictionary> dictionarys) {
        if ( dictionarys == null ) {
            return null;
        }

        List<DictionaryDTO> list = new ArrayList<DictionaryDTO>( dictionarys.size() );
        for ( Dictionary dictionary : dictionarys ) {
            list.add( entity2dto( dictionary ) );
        }

        return list;
    }
}
