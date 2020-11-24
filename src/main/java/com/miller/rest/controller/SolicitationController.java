package com.miller.rest.controller;

import com.miller.domain.repository.Solicitations;
import com.miller.service.SolicitationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitations")
public class SolicitationController {
    private final SolicitationService service;

    public SolicitationController(SolicitationService service) {
        this.service = service;
    }
}
