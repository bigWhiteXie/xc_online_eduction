package com.xuecheng.common.enums.content;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     课程计划发布状态
 * </p>
 *
 * @Description: 对课程计划发布状态常量信息使用枚举进行描述
 * @Author: Flippy
 * @Date: 2019/9/12 15:35
 * @since : 2019/9/12 15:35
 */
@StatusAnnotation(name = "课程计划发布状态",code="203")
public enum TeachPlanEnum implements StatusEnum {

    UNPUBLISH_STATUS("203001","未发布"),
    PUBLISH_STATUS("203002","已发布");



    public static final Integer FIRST_PARENTID_FLAG = 0;
    public static final Integer FIRST_LEVEL = 1;
    public static final Integer SECEND_LEVEL = 2;
    public static final Integer THIRD_LEVEL = 3;

    String code;
    String desc;

    TeachPlanEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
