package com.miller.service;

import com.miller.model.Client;
import com.miller.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private ClientsRepository repository;

    public ClientsService(ClientsRepository repository) {
        this.repository = repository;
    }

    public void saveClient(Client client) {
        validateClient(client);

        repository.persist(client);
    }

    public void validateClient(Client client) {

    }
}
