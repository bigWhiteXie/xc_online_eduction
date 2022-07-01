package com.xuecheng.common.enums.content;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     公共的枚举信息
 * </p>
 */
@StatusAnnotation(name = "课程等级", code = "204")
public enum CourseGradeEnum implements StatusEnum {

    INITIAL_LEVEL("204001","初级"),
    MIDDLE_LEVEL("204002","中级"),
    SENIOR_LEVEL("204003","高级");

    String code;
    String desc;

    CourseGradeEnum(String code, String desc) {
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
