package com.miller;

import com.miller.domain.entity.Client;
import com.miller.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SellerApplication {

    @Bean
    CommandLineRunner init(@Autowired Clients clients) {
        return args -> {

            Client client = new Client("Miller");

            clients.save(client);

            List<Client> clientList = clients.findAll();

            clientList.forEach(System.out::println);

            client.setName("Miller Cesar");

            clients.save(client);

            clientList = clients.findUserByName("Miller Cesar");

            clientList.forEach(System.out::println);

            boolean exists = clients.existsByName("Miller Cesar");

            System.out.println("Client exists: " + exists);

            clients.delete(client);

            clientList = clients.findAll();

            clientList.forEach(System.out::println);

            exists = clients.existsByName("Miller Cesar");

            System.out.println("Client exists: " + exists);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }
}
