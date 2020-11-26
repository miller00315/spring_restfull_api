package com.miller.domain.repository;

import com.miller.domain.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUser, Integer> {

    Optional<ApiUser> findByUsername(String username);
}
