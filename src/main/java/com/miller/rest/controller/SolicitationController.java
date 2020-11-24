package com.miller.rest.controller;

import com.miller.domain.entity.Solicitation;
import com.miller.domain.entity.SolicitedItem;
import com.miller.domain.enums.SolicitationStatus;
import com.miller.rest.dto.SolicitationRequestDTO;
import com.miller.rest.dto.SolicitationResponseDTO;
import com.miller.rest.dto.SolicitationUpdateStatusDTO;
import com.miller.rest.dto.SolicitedItemResponseDTO;
import com.miller.service.SolicitationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/solicitations")
public class SolicitationController {
    private final SolicitationService service;

    public SolicitationController(SolicitationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer saveSolicitation(@RequestBody @Valid SolicitationRequestDTO dto) {
        return service.saveSolicitation(dto).getId();
    }

    @GetMapping(value = "{id}")
    public SolicitationResponseDTO getById(@PathVariable Integer id) {
        return service.getCompleteService(id)
                .map(this::converter)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Solicitation not found"));
    }

    @PatchMapping(value = "{id}") // Update only same fields
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@RequestBody SolicitationUpdateStatusDTO dto, @PathVariable Integer id) {
        service.updateStatus(id, SolicitationStatus.valueOf(dto.getStatus()));
    }

    private SolicitationResponseDTO converter(Solicitation solicitation) {

         return  SolicitationResponseDTO
                        .builder()
                        .id(solicitation.getId())
                        .cpf(solicitation.getClient().getCpf())
                        .clientName(solicitation.getClient().getName())
                        .total(solicitation.getTotal())
                        .solicitedAt(solicitation.getSolicited_at())
                        .status(solicitation.getStatus().name())
                        .items(converter(solicitation.getSolicitedItems()))
                        .build();
    }

    private List<SolicitedItemResponseDTO> converter(List<SolicitedItem> items) {

        if(items.isEmpty())
            return Collections.emptyList();

       return items.stream()
               .map(item -> SolicitedItemResponseDTO
                       .builder()
                       .description(item.getProduct().getDescription())
                       .unityPrice(item.getProduct().getUnity_price())
                       .quantity(item.getQuantity())
                       .build())
               .collect(Collectors.toList());
    }
}
