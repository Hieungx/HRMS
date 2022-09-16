package com.user.domain.service.feign;

import com.user.app.dtos.AccessRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${auth-client.base-url}")
public interface AuthClient {
    @PostMapping(value = "/access-request")
    boolean accessCheck(@RequestBody AccessRequestDTO accessRequestDTO);
}
