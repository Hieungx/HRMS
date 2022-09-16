package com.user.app.responses;

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
public class BusinessResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public static BusinessResponse ok(Object data) {
        return BusinessResponse.builder().code(String.valueOf(HttpStatus.OK.value())).message("Successful").data(data).build();
    }
    public static BusinessResponse error(String code, String message) {
        return BusinessResponse.builder().code(code).message(message).build();
    }
}
