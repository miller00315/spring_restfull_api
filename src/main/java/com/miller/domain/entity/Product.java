package com.miller.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "unity_price")
    private BigDecimal unity_price;

    @OneToMany(mappedBy = "product")
    private Set<SolicitedItem> solicitedItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnity_price() {
        return unity_price;
    }

    public void setUnity_price(BigDecimal unity_price) {
        this.unity_price = unity_price;
    }

    public Set<SolicitedItem> getSolicitedItems() {
        return solicitedItems;
    }

    public void setSolicitedItems(Set<SolicitedItem> solicitedItems) {
        this.solicitedItems = solicitedItems;
    }
}
