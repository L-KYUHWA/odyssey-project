package com.backgom.odyssey.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {
	private StatusCode statusCode;
	private String statusMessage;
	private Object response;

	public ResponseEntity(Object result) {
		this.statusCode = StatusCode.SUCCESS;
		this.statusMessage = StatusCode.SUCCESS.getMessage();
		this.response = result;
	}

	public ResponseEntity(StatusCode statusCode) {
		this.statusCode = statusCode;
		this.statusMessage = statusCode.getMessage();
	}
}
