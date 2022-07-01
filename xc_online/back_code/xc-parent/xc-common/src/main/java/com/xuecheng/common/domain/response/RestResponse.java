package com.xuecheng.common.domain.response;

import com.xuecheng.common.domain.code.CommonErrorCode;
import com.xuecheng.common.domain.code.ErrorCode;

/**
 * 响应通用参数包装
 */
public class RestResponse<T> {

	/**
	 * 响应错误编码,0为正常
	 */
	private int code;

	/**
	 * 响应错误信息
	 */
	private String msg;

	/**
	 * 响应内容
	 */
	private T result;

	/**
	 * 错误信息的封装
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static <T> RestResponse<T> validfail(String msg) {
		RestResponse<T> response = new RestResponse<T>();
		response.setCode(-2);
		response.setMsg(msg);
		return response;
	}

	/**
	 * 对ErrorCode信息的封装
	 * @param errorCode {@link ErrorCode} 业务错误信息
	 * @return RestResponse Rest服务封装相应数据
	 */
	public static <T> RestResponse<T> validfail(ErrorCode errorCode) {
		RestResponse<T> response = new
				RestResponse<T>(errorCode.getCode(),errorCode.getDesc());
		return response;
	}

	/**
	 * 添加正常响应数据（包含响应内容）
	 * @return RestResponse Rest服务封装相应数据
	 */
	public static <T> RestResponse<T> success(T result) {
		RestResponse<T> response = new RestResponse<T>();
		response.setResult(result);
		return response;
	}

	/**
	 * 添加正常响应数据（不包含响应内容）
	 * @return RestResponse Rest服务封装相应数据
	 */
	public static <T> RestResponse<T> success() {
		return new RestResponse<T>();
	}




	public RestResponse() {
		this(0, "success");
	}

	public RestResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Boolean isSuccessful() {
		return this.code == CommonErrorCode.SUCCESS.getCode();
	}

	@Override
	public String toString() {
		return "RestResponse [code=" + code + ", msg=" + msg + ", result="
				+ result + "]";
	}

}
