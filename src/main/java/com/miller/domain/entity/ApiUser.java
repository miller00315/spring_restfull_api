package com.miller.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "apiUser")
public class ApiUser {
    @Id //Define o identificador da entidade
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @NotEmpty(message = "{field.user.name.empty}")
    private String userName;

    @Column
    @NotEmpty(message = "{field.password.empty}")
    private String password;

    @Column
    private boolean admin;
}
