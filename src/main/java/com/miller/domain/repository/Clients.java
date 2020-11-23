package com.miller.domain.repository;

import com.miller.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clients extends JpaRepository<Client, Integer> {

    @Query(value = "select c from Client c where c.name like :name") // Consulta hql
    List<Client> findUserByName( @Param(value = "name") String name);

    @Query(value = " delete from Client c where c.name =:name ")
    @Modifying
    void deleteByName(String name);

    boolean existsByName(String name);
}
