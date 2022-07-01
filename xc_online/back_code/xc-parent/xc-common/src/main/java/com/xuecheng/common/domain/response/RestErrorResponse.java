package com.xuecheng.common.domain.response;

import com.xuecheng.common.domain.code.ErrorCode;

/**
 * 错误响应参数包装
 */
public class RestErrorResponse {

    private String errCode;

    private String errMessage;


    public RestErrorResponse(String errCode,String errMessage){
        this.errCode = errCode;
        this.errMessage= errMessage;
    }

    public RestErrorResponse(ErrorCode errorCode){
        this.errCode = String.valueOf(errorCode.getCode());
        this.errMessage= errorCode.getDesc();
    }


    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
