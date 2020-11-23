package com.miller.domain.repository;

import com.miller.domain.entity.Client;
import com.miller.domain.entity.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Solicitations extends JpaRepository<Solicitation, Integer>{
    Set<Solicitation> findByClient(Client client);
}
