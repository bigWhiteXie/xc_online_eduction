package com.xuecheng.common.enums.content;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     课程模式状态
 * </p>
 */
@StatusAnnotation(name = "课程模式状态",code="200")
public enum CourseModeEnum implements StatusEnum {

    COURSE_MODE_COMMON_STATUS("200001","普通"),
    COURSE_MODE_RECORD_STATUS("200002","录播"),
    COURSE_MODE_LIVE_STATUS("200003","直播"),
    ;

    String code;
    String desc;

    CourseModeEnum(String code, String desc) {
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


    public static CourseModeEnum getTeachModeEnum(String sid) {
        if (sid == null) {
            return null;
        }
        for (CourseModeEnum ctme : CourseModeEnum.values()) {
            if (ctme.getCode().equals(sid.trim())) {
                return ctme;
            }
        }
        return null;
    }


}
