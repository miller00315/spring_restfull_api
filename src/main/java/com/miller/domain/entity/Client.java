package com.miller.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="client")
public class Client {
    @Id //Define o identificador da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(length = 100, name = "name")
    @NotEmpty(message = "{field.name.empty}")
    private String name;

    @Column(length = 11, name = "cpf")
    @NotEmpty(message = "{field.cpf.empty}")
    @CPF(message = "{field.cpf.invalid}")
    private String cpf;

    @JsonIgnore // Parser will ignore this property
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY) // One para a classse atual ToMany classe mapeada, mappedBy
    private Set<Solicitation> solicitations;
}
