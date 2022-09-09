package com.spring.restTemplate;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RestTemplateUtils {

	public Object executeApi(String url, HttpMethod httpMethod, Map<String, Object> variable) {
		RestTemplate restTemplate = new RestTemplate();
		var obj = restTemplate.exchange(url, httpMethod, null, String.class, variable);
		return obj;
	}
	
}
