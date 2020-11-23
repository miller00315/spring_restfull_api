package com.miller.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Solicitation {

    private Integer id;
    private Client client;
    private LocalDate solicited_at;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getSolicited_at() {
        return solicited_at;
    }

    public void setSolicited_at(LocalDate solicited_at) {
        this.solicited_at = solicited_at;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
