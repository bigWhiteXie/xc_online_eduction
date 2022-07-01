package com.xuecheng.common.enums.common;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     资源类型状态
 * </p>
 *
 * @Description: 对资源类型状态常量信息使用枚举进行描述
 */
@StatusAnnotation(name = "资源类型状态",code="001")
public enum ResourceTypeEnum implements StatusEnum {

    // 直播001001; 视频001002; 文档001003; 作业001004;

    LIVE("001001","直播"),
    VIDEO("001002","视频"),
    DOCUMENT("001003","文档"),
    WORK("001004","作业"),
    ;

    String code;
    String desc;

    ResourceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
