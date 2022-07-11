package com.xuecheng.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.system.model.dto.CourseCategoryDTO;
import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.response.RestResponse;
import com.xuecheng.common.util.StringUtil;
import com.xuecheng.system.convert.CourseCategoryConvert;
import com.xuecheng.system.entity.CourseCategory;
import com.xuecheng.system.entity.ext.CourseCategoryNode;
import com.xuecheng.system.mapper.CourseCategoryMapper;
import com.xuecheng.system.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {



    public List<CourseCategoryDTO> queryTreeNodes() {

        // 如何在mp对service封装中来获得mapper自定义的方法
        // mp中的service层封装里有个方法可以获得mapper的代理对象：getBaseMapper
        CourseCategoryMapper baseMapper = this.getBaseMapper();

        List<CourseCategoryNode> courseCategoryNodes = baseMapper.selectTreeNodes();

        List<CourseCategoryDTO> dtos = CourseCategoryConvert.INSTANCE.nodes2dtos(courseCategoryNodes);

        return dtos;
    }


    public RestResponse<CourseCategoryDTO> getCourseCategoryById4s(String id) {

        // 1.判断关键数据
        if (StringUtil.isBlank(id)) {
            return RestResponse.validfail(CommonErrorCode.E_100101);
        }

        // 2.根据id查询数据
        CourseCategory courseCategory = this.getById(id);

        // 3.判断查询后的数据
        if (ObjectUtils.isEmpty(courseCategory)) {
            return RestResponse.validfail("课程分类不存在");
        } else {

            CourseCategoryDTO dto = CourseCategoryConvert.INSTANCE.entity2dto(courseCategory);

            return RestResponse.success(dto);
        }

    }
}
