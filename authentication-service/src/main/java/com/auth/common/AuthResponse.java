package com.auth.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static AuthResponse ok(Object data) {
        return AuthResponse.builder().code(HttpStatus.OK.value()).message("Successful").data(data).build();
    }

    public static AuthResponse error(Integer code, String message) {
        return AuthResponse.builder().code(code).message(message).build();
    }
    public static AuthResponse buildAll(Integer code, String message, Object data) {
        return AuthResponse.builder().code(code).message(message).data(data).build();
    }
}
