package com.auth.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationException extends RuntimeException {
    String exceptionCode;
    String exceptionMessage;
}
