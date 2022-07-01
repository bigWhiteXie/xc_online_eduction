package com.xuecheng.common.enums.common;

import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *     审核状态
 * </p>
 *
 * @Description: 对审核状态使用枚举进行描述
 */
public enum AuditEnum implements StatusEnum {

    AUDIT_DISPAST_STATUS("202001","审核未通过"),
    AUDIT_UNPAST_STATUS("202002","未审核"),
    AUDIT_PASTED_STATUS("202003","审核通过"),
    ;

    String code;
    String desc;

    AuditEnum(String code, String desc) {
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
