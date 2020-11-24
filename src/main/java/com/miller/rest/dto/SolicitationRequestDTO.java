package com.miller.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitationRequestDTO { // Represents a request of Solicitation
    private Integer client;
    private BigDecimal total;
    private List<SolicitationItemRequestDTO> items;
}
