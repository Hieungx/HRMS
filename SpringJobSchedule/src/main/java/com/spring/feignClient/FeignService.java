package com.spring.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="feign", url = "http://localhost:9091/api/v1/feign-client-test")
public interface FeignService {
	
	@GetMapping()
	Object feignTesting();
}
