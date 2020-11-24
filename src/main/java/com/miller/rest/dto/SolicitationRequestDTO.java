package com.miller.rest.dto;

import com.miller.validation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitationRequestDTO { // Represents a request of Solicitation
    @NotNull(message = "{field.client.code.empty}")
    private Integer client;

    @NotNull(message = "{field.total.solicitation.empty}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.solicitation.items.empty}")
    private List<SolicitationItemRequestDTO> items;
}
