package com.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/redis")
public class RedisController {

	@Autowired
	private ContentCacheRedisConfigBean redisConfigBean;

	@Autowired
	private ContentCacheRedisConfigBean redis;

	@GetMapping(value = "/set")
	public Object set(@RequestParam(name = "key") String key, @RequestParam(name = "value") String value,
			@RequestParam(name = "expiredTime") int expiredTime) {
		redisConfigBean.set(key, expiredTime, value);
		return "Set success";
	}

	@GetMapping(value = "/get")
	public Object get(@RequestParam(name = "key") String key) {
		if (!redisConfigBean.exists(key)) {
			return "Không tồn tại hoặc đã hết hạn";
		}
		return redisConfigBean.get(key);
	}
}
