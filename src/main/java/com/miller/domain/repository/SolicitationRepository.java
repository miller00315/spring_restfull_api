package com.miller.domain.repository;

import com.miller.domain.entity.Client;
import com.miller.domain.entity.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SolicitationRepository extends JpaRepository<Solicitation, Integer>{
    @Query ("select s from Solicitation s left join fetch s.solicitedItems where s.id = :id")
    Optional<Solicitation> findByIdFetchSolicitedItems(@Param("id") Integer id);
}
