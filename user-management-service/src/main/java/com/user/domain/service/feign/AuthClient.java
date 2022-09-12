package com.user.domain.service.feign;

import com.user.app.dtos.AuthUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authClient", url = "${auth-client.base-url}")
public interface AuthClient {
    String AUTH_TOKEN = "Authorization";

    @GetMapping(value = "/user-info")
    AuthUserDTO userInfo(@RequestHeader(AUTH_TOKEN) String bearerToken);
}
