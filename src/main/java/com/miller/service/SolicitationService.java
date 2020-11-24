package com.miller.service;

import com.miller.domain.entity.Solicitation;
import com.miller.domain.enums.SolicitationStatus;
import com.miller.rest.dto.SolicitationRequestDTO;

import java.util.Optional;

public interface SolicitationService {
    Solicitation saveSolicitation(SolicitationRequestDTO dto);
    Optional<Solicitation> getCompleteService(Integer id);
    void updateStatus(Integer id, SolicitationStatus status);
}
