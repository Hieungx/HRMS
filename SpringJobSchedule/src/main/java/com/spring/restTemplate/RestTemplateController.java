package com.spring.restTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RestTemplateController {

	public static final String BASE_API = "http://localhost:9999/api/v1/feign-client-test";
	@Autowired
	RestTemplateUtils restTemplateUtils;
	
	@GetMapping("/rest-template")
	public Object test() {
		 Map<String, Object> variable = new HashMap<>();
		return restTemplateUtils.executeApi(BASE_API, HttpMethod.GET, variable);
	}
	
}
