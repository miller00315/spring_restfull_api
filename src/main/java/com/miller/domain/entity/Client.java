package com.miller.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id //Define o identificador da entidade
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(length = 100)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(length = 11)
    @NotEmpty(message = "CPF cannot be empty")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @JsonIgnore // Parser will ignore this property
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY) // One para a classse atual ToMany classe mapeada, mappedBy
    private Set<Solicitation> solicitations;
}
