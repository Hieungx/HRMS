package com.spring.feignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-client")
public class FeignController {

	@Autowired
	private FeignService feignService;
	
	@GetMapping
	public Object feignClient() {
		var obj = feignService.feignTesting();
		System.out.println(obj);
		return obj;
	}
}
