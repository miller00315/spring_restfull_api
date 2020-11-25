package com.miller.domain.repository;

import com.miller.domain.entity.SolicitedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitedItemRepository extends JpaRepository<SolicitedItem, Integer>{
}
