package com.miller.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
