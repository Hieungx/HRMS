package com.user.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private UUID userId;
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private String roles;
    private String accessToken;
    private String refreshToken;
}
