package com.miller;

import com.miller.domain.entity.Client;
import com.miller.domain.entity.Solicitation;
import com.miller.domain.repository.Clients;
import com.miller.domain.repository.Solicitations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SellerApplication {

    @Bean
    CommandLineRunner init(
            @Autowired Clients clients,
            @Autowired Solicitations solicitations) {
        return args -> {

            Client client = new Client("Miller");

            clients.save(client);

            List<Client> clientList = clients.findAll();

            clientList.forEach(System.out::println);

            client.setName("Miller Cesar");

            clients.save(client);

            Solicitation solicitation = new Solicitation();

            solicitation.setClient(client);
            solicitation.setSolicited_at(LocalDate.now());
            solicitation.setTotal(BigDecimal.valueOf(1));

            solicitations.save(solicitation);

            Client solicitationClient = clients.findClientFetchSolicitation(client.getId());

            System.out.println(solicitationClient);
            System.out.println(solicitationClient.getSolicitations());

            clientList = clients.findClientByName("Miller Cesar");

            clientList.forEach(System.out::println);

            boolean exists = clients.existsByName("Miller Cesar");

            Set<Solicitation> solicitationsItems = solicitations.findByClient(client);

            solicitationsItems.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class, args);
    }
}
