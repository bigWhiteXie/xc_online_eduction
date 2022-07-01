package com.xuecheng.common.exception;

import com.xuecheng.common.domain.code.ErrorCode;

/**
 * <p>异常抛出的工具类</p>
 *
 * @Description: 异常抛出的工具类，简化业务异常抛出代码
 */
public class ExceptionCast {

    private  ExceptionCast() {
    }

    /**
     * 抛出业务异常类，并传入业务错误信息（枚举）
     * @param errorCode {@link Enum} 业务错误信息
     */
    public static void cast(ErrorCode errorCode){
        throw new BusinessException(errorCode);
    }

    public static void cast(boolean expression, ErrorCode errorCode){
        if (expression) {
            throw new BusinessException(errorCode);
        }
    }

    /**
     * 抛出业务异常类，并传入业务错误信息（枚举）和异常信息
     * @param errorCode {@link Enum} 业务错误信息
     * @param throwable {@link Throwable} 异常信息
     */
    public static void castWithException(ErrorCode errorCode,Throwable throwable){
        throw new BusinessException(errorCode,throwable);
    }

    /**
     * 抛出业务异常类，并传入业务错误信息（枚举）和异常信息（字符串信息）
     * @param errorCode {@link Enum} 业务错误信息
     * @param msg {@link String} 异常信息
     */
    public static void castWithExceptionMsg(ErrorCode errorCode,String msg){
        throw new BusinessException(errorCode,msg);
    }


    /**
     * 适配Rest服务调用的业务异常信息抛出
     * @param code
     * @param desc
     */
    public static void castWithCodeAndDesc(int code,String desc){
        throw new BusinessException(code,desc);
    }


}
