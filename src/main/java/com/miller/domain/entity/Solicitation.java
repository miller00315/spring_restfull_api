package com.miller.domain.entity;

import com.miller.domain.enums.SolicitationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="solicitation")
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "{field.client.code.empty}")
    @ManyToOne // Many atual ToOne outra entidade mapeada
    @JoinColumn(name = "client_id")
    private Client client;


    @NotNull(message = "{field.solicited.at.empty}" )
    @Column(name = "solicited_at")
    private LocalDate solicited_at;

    @NotNull(message = "{field.total.solicitation.empty}")
    @Column(name = "TOTAL", precision = 20, scale = 2) // Precisão quantidade de casas e scale casas decimais
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private SolicitationStatus status;

    @OneToMany(mappedBy = "solicitation")
    private List<SolicitedItem> solicitedItems;
}
