package com.miller.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Client {
    @Id //Define o identificador da entidade
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "client") // One para a classse atual ToMany classe mapeada, mappedBy
    private Set<Solicitation> solicitations;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Solicitation> getSolicitations() {
        return solicitations;
    }

    public void setSolicitations(Set<Solicitation> solicitations) {
        this.solicitations = solicitations;
    }

    @Override
    public String toString() {
        return "Client{ id:" + id + ", name: " + name + "} \n";
    }
}
