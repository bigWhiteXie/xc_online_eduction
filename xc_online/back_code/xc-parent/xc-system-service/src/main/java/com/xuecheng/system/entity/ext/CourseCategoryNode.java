package com.xuecheng.system.entity.ext;

import com.xuecheng.system.entity.CourseCategory;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@Data
public class CourseCategoryNode extends CourseCategory {

    /**
     * 子节点数据集合，课程分类为3级分类
     * 为方便树形结构数据的返回，定义子节点的集合属性
     */
    List<CourseCategoryNode> childrenTreeNodes;

}
