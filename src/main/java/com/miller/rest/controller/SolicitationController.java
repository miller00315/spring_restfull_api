package com.miller.rest.controller;

import com.miller.domain.repository.Solicitations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitations")
public class SolicitationController {

    private final Solicitations repository;

    public SolicitationController(Solicitations repository) {
        this.repository = repository;
    }
}
