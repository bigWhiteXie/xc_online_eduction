package com.xuecheng.api.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author itcast
 */
@Data
@ApiModel(value="CourseCategoryDTO", description="课程分类")
public class CourseCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String courseCategoryId;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类标签默认和名称一样")
    private String label;

    @ApiModelProperty(value = "父结点id（第一级的父节点是0，自关联字段id）")
    private String parentid;

    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    @ApiModelProperty(value = "排序字段")
    private Integer orderby;

    @ApiModelProperty(value = "是否叶子")
    private Integer isLeaf;

    @ApiModelProperty(value = "课程分类子节点数据")
    private List categoryTreeNodes;

}
