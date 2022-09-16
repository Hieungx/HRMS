package com.auth.domain.repositories;

import com.auth.domain.entities.DynamicAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicAuthorizationRepository extends JpaRepository<DynamicAuthorization, Integer> {
    DynamicAuthorization findByUriAndMethod(String requestURI, String method);
}
