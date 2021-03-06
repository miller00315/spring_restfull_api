package com.miller.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "description")
    @NotEmpty(message = "{field.description.empty}")
    private String description;

    @Column(name = "unity_price")
    @NotNull(message = "{field.price.empty}")
    private BigDecimal unity_price;

    @OneToMany(mappedBy = "product")
    private Set<SolicitedItem> solicitedItems;
}
