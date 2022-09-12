package com.auth.domain.repositories;

import com.auth.domain.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    AuthUser findByUsername(String username);

    AuthUser findByEmail(String email);

    AuthUser findByPhone(String phone);
}
