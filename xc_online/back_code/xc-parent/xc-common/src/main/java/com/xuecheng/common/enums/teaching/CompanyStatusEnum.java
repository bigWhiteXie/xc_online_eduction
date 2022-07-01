package com.xuecheng.common.enums.teaching;

import com.baomidou.mybatisplus.annotation.EnumValue;


public enum CompanyStatusEnum {
    CREATED("0", "新建");

    @EnumValue
    String statusCode;
    String statusMsg;
    CompanyStatusEnum(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
