package com.miller.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne // Many atual ToOne outra entidade mapeada
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "solicited_at")
    private LocalDate solicited_at;

    @Column(name = "total", length = 20, precision = 2) // Tamanho e precisão das casas decimais
    private BigDecimal total;

    @OneToMany(mappedBy = "solicitation")
    private Set<SolicitedItem> solicitedItems;

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
