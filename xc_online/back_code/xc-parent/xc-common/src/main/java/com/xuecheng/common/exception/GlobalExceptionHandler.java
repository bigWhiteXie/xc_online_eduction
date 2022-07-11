package com.xuecheng.common.exception;

import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.code.ErrorCode;
import com.xuecheng.common.domain.response.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	/**
	 * 处理参数校验失败的异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RestErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
		// 从异常对象中拿到ObjectError对象
		ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
		// 然后提取错误提示信息进行返回
		return new RestErrorResponse("120018", objectError.getDefaultMessage());
	}

//	@ExceptionHandler(RuntimeException.class)
//	public String runTimeException(RuntimeException e) {
//
//		// 然后提取错误提示信息进行返回
//		return e.getMessage();
//	}



}
