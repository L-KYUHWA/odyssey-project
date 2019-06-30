package com.backgom.odyssey.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		//CLIENT ERROR = 4xx
		//SERVER ERROR = 5xx
		return (response.getStatusCode().series() == CLIENT_ERROR)
				|| (response.getStatusCode().series() == SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		//doNoting
	}

	@Override
	public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
		HttpStatus.Series series = response.getStatusCode().series();

		if (series == CLIENT_ERROR || series == SERVER_ERROR) {
			log.error("[API Provider Response Error] An error has occurred in the API Provider. " +
					"httpMethod : {}, Url : {}, Response status code : {}", method, url, response.getStatusCode());
		}

	}
}
