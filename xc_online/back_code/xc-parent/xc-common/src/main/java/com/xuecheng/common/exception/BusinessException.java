

package com.xuecheng.common.exception;


import com.xuecheng.common.domain.code.ErrorCode;

public class BusinessException extends RuntimeException {

	
	private static final long serialVersionUID = 5565760508056698922L;
	
	private ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	/**
	 * 适配Rest服务业务异常信息的抛出
	 * @param code {@link String} 操作代码
	 * @param desc {@link String} 错误信息
	 */
	public BusinessException(int code,String desc) {
		super();

		//1.构建ErrorCode匿名内部类,并返回
		ErrorCode errorCode = new ErrorCode() {
			@Override
			public int getCode() {
				return code;
			}

			@Override
			public String getDesc() {
				return desc;
			}
		};

		this.errorCode = errorCode;
	}
	
	public BusinessException() {
		super();
	}

	public BusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public BusinessException(ErrorCode errorCode, String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.errorCode = errorCode;
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public BusinessException(ErrorCode errorCode, String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errorCode = errorCode;
	}

	public BusinessException(String arg0) {
		super(arg0);
	}
	
	public BusinessException(ErrorCode errorCode, String arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public BusinessException(Throwable arg0) {
		super(arg0);
	}
	
	public BusinessException(ErrorCode errorCode, Throwable arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}


	
}
