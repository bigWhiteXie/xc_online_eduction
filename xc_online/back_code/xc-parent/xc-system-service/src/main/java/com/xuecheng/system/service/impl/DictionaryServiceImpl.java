package com.xuecheng.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.system.model.dto.DictionaryDTO;
import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.util.StringUtil;
import com.xuecheng.system.convert.DictionaryConvert;
import com.xuecheng.system.entity.Dictionary;
import com.xuecheng.system.mapper.DictionaryMapper;
import com.xuecheng.system.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public List<DictionaryDTO> queryAll() {

        List<Dictionary> list = this.list();

        List<DictionaryDTO> dtos = DictionaryConvert.INSTANCE.entitys2dtos(list);

        return dtos;
    }

    @Override
    public DictionaryDTO getByCode(String code) {

        if (StringUtil.isBlank(code)) {
            throw new RuntimeException(CommonErrorCode.E_100101.getDesc());
        }

        LambdaQueryWrapper<Dictionary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dictionary::getCode, code);

        Dictionary dictionary = this.getOne(queryWrapper);

        DictionaryDTO dtoResult = DictionaryConvert.INSTANCE.entity2dto(dictionary);

        return dtoResult;
    }
}
