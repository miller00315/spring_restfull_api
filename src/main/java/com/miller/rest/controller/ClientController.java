package com.miller.rest.controller;

import com.miller.domain.entity.Client;
import com.miller.domain.repository.ClientRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Api("Clients api")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) { // Get from context because this class is a controller
        this.clientRepository = clientRepository;
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
    @ApiOperation("Get the client by your id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Client founded"),
            @ApiResponse(code = 404, message = "Client not find for this id")
    })
    public Client getClientById(@PathVariable Integer id){ // Direct mapping by name of variable

        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping // Reuse controller api url for post method
    @ResponseStatus(HttpStatus.CREATED) // Return the correct status code
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client saved"),
            @ApiResponse(code = 400, message = "Validation error")
    })
    public Client saveClient(@RequestBody @Valid Client client) { // Map from body of request
        return clientRepository.save(client);

    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable
                                 @ApiParam("Id do client") Integer id) {
       clientRepository.findById(id)
               .map(client -> {
                   clientRepository.delete(client);
                   return client;
               })
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@RequestBody @Valid Client client, @PathVariable Integer id) {
        clientRepository.findById(id).map(existClient -> {
            client.setId(existClient.getId());
            clientRepository.save(client);
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

        return clientRepository.findAll(example);
    }
}
