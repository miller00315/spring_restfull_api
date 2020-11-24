package com.miller.rest.controller;

import com.miller.domain.entity.Client;
import com.miller.domain.repository.Clients;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final Clients clients;

    public ClientController(Clients clients) { // Get from context because this class is a controller
        this.clients = clients;
    }

    @RequestMapping(
            value = {"/hello/{clientName}", "/hi/{clientName}"}, // Can direct the request to many urls
            method = RequestMethod.GET
    )
    public String helloClients(@PathVariable("clientName") String clientName) {
        return String.format("Hello %s", clientName);
    }

    @GetMapping(
        value = {"{id}"}
    )
    public Client getClientById(@PathVariable Integer id){ // Direct mapping by name of variable

        return clients.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping // Reuse controller api url for post method
    @ResponseStatus(HttpStatus.CREATED) // Return the correct status code
    public Client saveClient(@RequestBody @Valid Client client) { // Map from body of request
        return clients.save(client);

    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer id) {
       clients.findById(id)
               .map(client -> {
                   clients.delete(client);
                   return client;
               })
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@RequestBody @Valid Client client, @PathVariable Integer id) {
        clients.findById(id).map(existClient -> {
            client.setId(existClient.getId());
            clients.save(client);
            return client;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> searchClients(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Client> example = Example.of(filter, matcher);

        return clients.findAll(example);
    }
}
