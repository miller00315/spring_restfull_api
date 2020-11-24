package com.miller.rest.dto;

import com.miller.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class SolicitationDTO {
    private Integer client;
    private BigDecimal total;
    private List<SolicitationItemDTO> items;

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<SolicitationItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SolicitationItemDTO> items) {
        this.items = items;
    }
}
