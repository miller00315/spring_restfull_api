package com.miller.domain.entity;

import com.miller.domain.enums.SolicitationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Column(name = "total", precision = 20, scale = 2) // Precisão quantidade de casas e scale casas decimais
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SolicitationStatus status;

    @OneToMany(mappedBy = "solicitation")
    private List<SolicitedItem> solicitedItems;
}
