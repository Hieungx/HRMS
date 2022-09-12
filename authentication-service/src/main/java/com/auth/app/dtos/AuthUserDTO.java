package com.auth.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDTO {
    private UUID userId;

    private String accessToken;

    private String refreshToken;

    private String username;

    private String fullName;

    private String phone;

    private String email;

    private String roles;


    public AuthUserDTO(UUID userId, String username, String fullName, String phone, String email, String roles) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
    }
}
