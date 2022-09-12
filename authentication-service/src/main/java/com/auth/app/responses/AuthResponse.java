package com.auth.app.responses;

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
    private String code;
    private String message;
    private T data;

    public static AuthResponse ok(Object data) {
        return AuthResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message("Successful").data(data).build();
    }
    public static AuthResponse error(String code, String message) {
        return AuthResponse.builder().code(code).message(message).build();
    }
}
