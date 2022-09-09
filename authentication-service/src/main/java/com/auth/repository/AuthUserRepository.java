package com.auth.repository;

import com.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    AuthUser findByUsername(String username);
}
