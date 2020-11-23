package com.miller.domain.repository;

import com.miller.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clients extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    boolean existsByName(String name);
}
