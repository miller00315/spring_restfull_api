package com.miller.implementation;

import com.miller.domain.repository.Solicitations;
import com.miller.service.SolicitationService;
import org.springframework.stereotype.Service;

@Service
public class SolicitationServiceImplementation implements SolicitationService {
    private Solicitations solicitations;

    public SolicitationServiceImplementation(Solicitations solicitations) {
        this.solicitations = solicitations;
    }
}
