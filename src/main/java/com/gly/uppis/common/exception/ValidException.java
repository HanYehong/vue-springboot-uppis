package com.gly.uppis.common.exception;

/**
 * @author GuLiyun
 * @date 2019/3/26
 */
public class ValidException extends RuntimeException {
	public ValidException() {
	}

	public ValidException(String message) {
		super(message);
	}

	public ValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidException(Throwable cause) {
		super(cause);
	}

	public ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
