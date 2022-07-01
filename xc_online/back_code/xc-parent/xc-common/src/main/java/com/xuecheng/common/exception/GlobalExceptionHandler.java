package com.xuecheng.common.exception;

import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.code.ErrorCode;
import com.xuecheng.common.domain.response.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p></p>
 *
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestErrorResponse customException(BusinessException e) {

		log.error("【系统异常】{}",e.getMessage(),e);

		ErrorCode errorCode = e.getErrorCode();

		return new RestErrorResponse(errorCode);

	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestErrorResponse exception(Exception e) {

		log.error("【系统异常】{}",e.getMessage(),e);

		return new RestErrorResponse(String.valueOf(CommonErrorCode.UNKOWN.getCode()),
				CommonErrorCode.UNKOWN.getDesc()+":"+e.getMessage());

	}
}
