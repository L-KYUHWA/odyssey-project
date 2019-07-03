package com.backgom.odyssey.support;

public enum StatusCode {
	SUCCESS("S001"),
	UN_SUPPORT_URL("S600"),
	LOGIN_ERROR("S700"),
	BUSINESS_ERROR("S800"),
	UNKNOWN_ERROR("S999");

	private String message;

	StatusCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
