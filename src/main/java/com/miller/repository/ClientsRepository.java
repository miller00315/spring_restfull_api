package com.miller.repository;

import com.miller.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientsRepository {
    public void persist(Client client) {
        // Access the database and save the client
    }
}
