package com.backgom.odyssey.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResult {

	public static final RequestResult SUCCESS = new RequestResult();
	private Status status;
	private Object result;

	public RequestResult() {
		this.status = Status.SUCCESS;
	}

	public RequestResult(Object result) {
		this.status = Status.SUCCESS;
		this.result = result;
	}

	public enum Status {
		SUCCESS,
		FAIL
	}

}
