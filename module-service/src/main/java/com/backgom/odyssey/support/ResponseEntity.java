package com.backgom.odyssey.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {
	private String statusCode;
	private String statusMessage;
	private Object response;

	public ResponseEntity(Object result) {
		this.statusCode = StatusCode.SUCCESS.getCode();
		this.statusMessage = StatusCode.SUCCESS.getMessage();
		this.response = result;
	}

	public ResponseEntity(StatusCode statusCode) {
		this.statusCode = statusCode.getCode();
		this.statusMessage = statusCode.getMessage();
	}
}
