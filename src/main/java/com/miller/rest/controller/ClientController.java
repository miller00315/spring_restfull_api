package com.miller.rest.controller;

import com.miller.domain.entity.Client;
import com.miller.domain.repository.Clients;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    private Clients clients;

    public ClientController(Clients clients) { // Get from context because this class is a controller
        this.clients = clients;
    }

    @RequestMapping(
            value = {"/hello/{clientName}", "/hi/{clientName}"}, // Can direct the request to many urls
            method = RequestMethod.GET
    )
    @ResponseBody
    public String helloClients(@PathVariable("clientName") String clientName) {
        return String.format("Hello %s", clientName);
    }

    @GetMapping(
        value = {"/{id}"}
    )
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Integer id){ // Direct mapping by name of variable

        Optional<Client> client = clients.findById(id);

        if(client.isPresent())
            return ResponseEntity.ok(client.get()); // Get the client from optional

        return ResponseEntity.notFound().build();

    }

    @PostMapping // Reuse controller api url for post method
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody Client client) { // Map from body of request
        Client lastSaveClient = clients.save(client);

        return ResponseEntity.ok(lastSaveClient);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity deleteClient(@PathVariable Integer id) {
        Optional<Client> client = clients.findById(id);

        if(client.isPresent()) {
            clients.delete(client.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public  ResponseEntity updateClient(@RequestBody Client client, @PathVariable Integer id) {
        return clients.findById(id).map( existClient -> {

            client.setId(existClient.getId());
            clients.save(client);

            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity searchClients(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Client> example = Example.of(filter, matcher);

        List<Client> clientList = clients.findAll(example);

        return ResponseEntity.ok(clientList);
    }
}
