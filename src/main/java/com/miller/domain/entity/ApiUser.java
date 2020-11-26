package com.miller.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "api_user")
public class ApiUser {
    @Id //Define o identificador da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY par mysql
    @Column(name="id")
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "{field.user.name.empty}")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "{field.password.empty}")
    private String password;

    @Column(name = "admin")
    private boolean admin;
}
