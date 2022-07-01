package com.xuecheng.common.enums.teaching;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *      课程作业记录审批状态
 * </p>
 *
 * @Description:
 * @Author: Flippy
 * @Date: 2019/9/25 15:55
 * @since: 2019/9/25 15:55
 */
@StatusAnnotation(name = "课程作业记录审批状态",code="306")
public enum WorkRecordCorrectionStatusEnum implements StatusEnum {

    RECORD_CORRECT_UNSUBMIT("306001", "未提交"),
    RECORD_CORRECT_PENDING("306002", "待批改"),
    RECORD_CORRECT_PENDED("306003", "已批改"),
    ;

    String code;
    String desc;

    WorkRecordCorrectionStatusEnum(String code, String desc) {
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

    public static WorkRecordCorrectionStatusEnum getTeachModeEnum(String code) {
        if (code == null) {
            return null;
        }
        for (WorkRecordCorrectionStatusEnum ctme : WorkRecordCorrectionStatusEnum.values()) {
            if (ctme.getCode().equals(code.trim())) {
                return ctme;
            }
        }
        return null;
    }
}
