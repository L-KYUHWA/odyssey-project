package com.backgom.odyssey.configuration;

import com.backgom.odyssey.exception.AuthException;
import com.backgom.odyssey.exception.BusinessException;
import com.backgom.odyssey.support.ResponseEntity;
import com.backgom.odyssey.support.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity handleBusinessException(final BusinessException e) {
		log.error("BusinessException", e);
		return new ResponseEntity(StatusCode.BUSINESS_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("handleHttpRequestMethodNotSupportedException", e);
		return new ResponseEntity(StatusCode.UN_SUPPORT_URL);
	}

	@ExceptionHandler(AuthException.class)
	protected ResponseEntity handleAuthException(final AuthException e) {
		log.error("AuthException", e);
		return new ResponseEntity(StatusCode.LOGIN_ERROR);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity handleException(Exception e) {
		log.error("exception", e);
		return new ResponseEntity(StatusCode.UNKNOWN_ERROR);
	}

}
