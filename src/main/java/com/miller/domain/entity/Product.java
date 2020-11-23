package com.miller.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    private Integer id;
    @Column
    private String description;
    @Column
    private BigDecimal unity_price;

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
}
