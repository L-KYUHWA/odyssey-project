package com.backgom.odyssey.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("kakao.developers")
public class KakaoCertification {
	private String restApiKey;
	private String javascriptKey;
}
