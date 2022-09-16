package com.auth.domain.configuration.webSecurityConfiguration;

import com.auth.app.dtos.AccessRequestDTO;
import com.auth.app.dtos.AuthUserDTO;
import com.auth.domain.configuration.redisConfiguration.RedisRepository;
import com.auth.domain.entities.DynamicAuthorization;
import com.auth.domain.repositories.DynamicAuthorizationRepository;
import com.auth.domain.services.JwtService;
import com.auth.domain.utils.DataUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AuthorizationConfig {
    @Resource
    DynamicAuthorizationRepository dynamicAuthorizationRepository;
    @Resource
    JwtService jwtService;
    @Value("${jwt.prefix_token}")
    String prefixToken;

    @Resource
    RedisRepository redisRepository;

    public boolean authorizationAllPrivateApi(AccessRequestDTO request) {
        DynamicAuthorization dynamicAuthorization = dynamicAuthorizationRepository.findByUriAndMethod(request.getUri(), request.getMethod());
        List<String> rolesAccess = DataUtils.getRoles(dynamicAuthorization.getRoles());
        if (ObjectUtils.isEmpty(request.getAccessToken()) || !redisRepository.exists(request.getAccessToken().replace(prefixToken,""))) {
            return false;
        } else {
            AuthUserDTO authUserDTO = jwtService.getUserInfoFromToken(request.getAccessToken().replace(prefixToken, ""));
            List<String> rolesClient = DataUtils.getRoles(authUserDTO.getRoles());
            boolean access = false;
            for (String roleClient : rolesClient) {
                if (rolesAccess.contains(roleClient)) {
                    access = true;
                }
                if (access) {
                    break;
                }
            }
            if(access){
                return true;
            }else{
                return false;
            }
        }
    }

}
