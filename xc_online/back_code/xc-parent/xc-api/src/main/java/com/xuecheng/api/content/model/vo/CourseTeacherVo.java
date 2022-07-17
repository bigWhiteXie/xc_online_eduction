package com.xuecheng.api.content.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel(value="CourseTeacherVO", description="课程教师信息视图类，用于页面对课程教师基本信息添加和修改，操作的属性比较有限，不开放的属性不让操作")
public class CourseTeacherVo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "教师id不能为空")
    private Long courseTeacherId;

    /**
     * 课程标识
     */
    private Long courseId;



    /**
     * 教师标识
     */
    private String teacherName;

    /**
     * 教师职位
     */
    private String position;

    /**
     * 教师简介
     */
    private String introduction;

    /**
     * 照片
     */
    private String photograph;


}
