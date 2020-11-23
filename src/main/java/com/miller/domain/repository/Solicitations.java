package com.miller.domain.repository;

import com.miller.domain.entity.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Solicitations extends JpaRepository<Solicitation, Integer>{
}
