package com.miller.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "solicitation_id")
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private  Integer quantity;
}
