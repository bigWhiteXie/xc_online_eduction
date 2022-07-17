package com.xuecheng.media.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 直播课程
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("live_course")
public class LiveCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 推流地址
     */
    private String pushUrl;

    /**
     * 播放地址
     */
    private String playUrl;

    private Long companyId;

    private String companyName;

    /**
     * 课程计划标识
     */
    private Long teachplanId;

    /**
     * 课程标识
     */
    private Long courseId;

    private Long coursePubId;

    /**
     * 直播人
     */
    private String username;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程章节名称
     */
    private String teachplanName;

    /**
     * 直播开始时间
     */
    private LocalDateTime liveStart;

    /**
     * 直播结束时间
     */
    private LocalDateTime liveEnd;

    /**
     * 是否支持试学或预览
     */
    private String isPreview;


}
