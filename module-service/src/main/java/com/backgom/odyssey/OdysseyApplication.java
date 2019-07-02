package com.backgom.odyssey;

import com.backgom.odyssey.handler.RestTemplateResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableAsync
@SpringBootApplication
public class OdysseyApplication extends SpringBootServletInitializer {

    @Autowired
    private RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    public static void main(String[] args) {
        SpringApplication.run(OdysseyApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(restTemplateResponseErrorHandler);
        return restTemplate;
    }

}
