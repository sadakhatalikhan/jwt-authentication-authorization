package com.jwt.auth.exception;

public class SignUpException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275151706604447917L;

	private final String message;
	private final Integer code;

	public SignUpException(Integer code, String message) {
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
	
}
