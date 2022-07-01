package com.xuecheng.common.enums.content;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     课程是否收费
 * </p>
 *
 * @Description: 对课程发布状态常量信息使用枚举进行描述
 * @Author: zhf
 * @Date: 2019/10/10 17:49
 */
@StatusAnnotation(name = "课程收费情况", code="201")
public enum CourseChargeEnum implements StatusEnum {
    CHARGE_NO("201000","免费"),
    CHARGE_YES("201001","收费");

    String code;
    String desc;

    CourseChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
