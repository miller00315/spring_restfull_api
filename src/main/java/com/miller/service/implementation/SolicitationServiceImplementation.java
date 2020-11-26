package com.miller.service.implementation;

import com.miller.domain.entity.Client;
import com.miller.domain.entity.Product;
import com.miller.domain.entity.Solicitation;
import com.miller.domain.entity.SolicitedItem;
import com.miller.domain.enums.SolicitationStatus;
import com.miller.domain.repository.ClientRepository;
import com.miller.domain.repository.ProductRepository;
import com.miller.domain.repository.SolicitationRepository;
import com.miller.domain.repository.SolicitedItemRepository;
import com.miller.exception.BusinessLogicException;
import com.miller.exception.SolicitationNotFoundException;
import com.miller.rest.dto.SolicitationItemRequestDTO;
import com.miller.rest.dto.SolicitationRequestDTO;
import com.miller.service.SolicitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitationServiceImplementation implements SolicitationService {
    private final SolicitationRepository solicitationsRepository;
    private final ClientRepository clientsRepository;
    private final ProductRepository productRepository;
    private final SolicitedItemRepository solicitedItemsRepository;

    @Override
    @Transactional // All execute or execute a rollback
    public Solicitation saveSolicitation(SolicitationRequestDTO dto) {
        Solicitation solicitation = new Solicitation();

        if(dto.getClient() == null)
            throw new BusinessLogicException("Client cannot be null");

        Client client = clientsRepository.findById(dto.getClient())
                .orElseThrow(() -> new BusinessLogicException("Invalid client code"));

        solicitation.setTotal(dto.getTotal());
        solicitation.setSolicited_at(LocalDate.now());
        solicitation.setClient(client);
        solicitation.setStatus(SolicitationStatus.COMPLETED);

        List<SolicitedItem> solicitedItems = convertItems(solicitation, dto.getItems());

        solicitationsRepository.save(solicitation);
        solicitedItemsRepository.saveAll(solicitedItems);

        solicitation.setSolicitedItems(solicitedItems);

        return solicitation;
    }

    @Override
    public Optional<Solicitation> getCompleteService(Integer id) {
        return solicitationsRepository.findByIdFetchSolicitedItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, SolicitationStatus status) {
        solicitationsRepository.findById(id).map(solicitation -> {
            solicitation.setStatus(status);
            solicitationsRepository.save(solicitation);
            return Void.TYPE;
        }).orElseThrow(SolicitationNotFoundException::new);
    }

    private List<SolicitedItem> convertItems(Solicitation solicitation, List<SolicitationItemRequestDTO> items) {
        if(items.isEmpty())
            throw new BusinessLogicException("List of items is empty");

        return items
                .stream()
                .map(dto -> {

                    Integer itemId = dto.getProduct();

                    Product product =
                            productRepository.findById(itemId)
                            .orElseThrow(() ->
                                    new BusinessLogicException(String.format("Invalid product: %d", itemId)
                                    )
                            );

                    SolicitedItem solicitedItem = new SolicitedItem();

                    solicitedItem.setQuantity(dto.getQuantity());
                    solicitedItem.setSolicitation(solicitation);
                    solicitedItem.setProduct(product);

                    return solicitedItem;
                })
                .collect(Collectors.toList());
    }



}
