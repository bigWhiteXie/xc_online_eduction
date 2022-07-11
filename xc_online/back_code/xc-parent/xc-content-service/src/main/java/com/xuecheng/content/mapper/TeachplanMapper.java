package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.entity.Teachplan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    @Select("select max(orderby) from teachplan where parentid=#{parentId} and grade=#{grade}")
    Integer selectMaxOrderby(@Param("parentId") long parentId,@Param("grade") int grade);
}
