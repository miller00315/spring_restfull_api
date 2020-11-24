package com.miller.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationResponseDTO {
    private Integer id;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private LocalDate solicitedAt;
    private List<SolicitedItemResponseDTO> items;
    private String status;
}
