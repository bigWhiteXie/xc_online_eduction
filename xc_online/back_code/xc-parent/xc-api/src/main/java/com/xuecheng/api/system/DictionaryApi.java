package com.xuecheng.api.system;

import com.xuecheng.api.system.model.dto.DictionaryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@Api(tags = "系统管理api服务")
public interface DictionaryApi {

    @ApiOperation("查询所有数据字典内容")
    List<DictionaryDTO> queryAll();

    @ApiOperation("根据code查询数据字典")
    @ApiImplicitParam(name = "code",value = "数据字典code",required = true,dataType = "String",paramType = "path")
    DictionaryDTO getByCode(String code);
}
