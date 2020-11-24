package com.miller.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitationItemRequestDTO {
    private Integer product;
    private Integer quantity;
}
