package com.backgom.odyssey.support;

public enum StatusCode {
	SUCCESS("S001", "Success"),
	UNSUPPORTED_URL("S600", "This URL is an unsupported URL"),
	LOGIN_ERROR("S700", "Please check ID or Password"),
	BUSINESS_ERROR("S800", "Server Issue"),
	UNKNOWN_ERROR("S999", "Unknown Error");

	private String code;
	private String message;

	StatusCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
}
