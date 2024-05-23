package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;



import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Override
    @Async
    public CompletableFuture<SubscriptionBox> save(SubscriptionBox subscriptionBox) {
        return CompletableFuture.completedFuture(subscriptionBoxRepository.save(subscriptionBox));
    }

    @Override
    @Async
    public CompletableFuture<Optional<SubscriptionBoxDTO>> findById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }

        return subscriptionBoxRepository.findById(id)
                .map(subscriptionBox -> CompletableFuture.completedFuture(Optional.of(DTOMapper.convertModelToDto(subscriptionBox))))
                .orElse(CompletableFuture.completedFuture(Optional.empty()));
//
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> findAll() {
        List<SubscriptionBox> subscriptionBoxes = subscriptionBoxRepository.findAll();
        return CompletableFuture.completedFuture(subscriptionBoxes);
    }

    @Override
    @Async
    public CompletableFuture<SubscriptionBox> update(SubscriptionBoxDTO subscriptionBoxDTO) {
//        if (subscriptionBox == null) {
//            throw new IllegalArgumentException("SubscriptionBox cannot be null");
//        }
//        return CompletableFuture.completedFuture(subscriptionBoxRepository.update(subscriptionBox));
        if (subscriptionBoxDTO == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        return subscriptionBoxRepository.findById(subscriptionBoxDTO.getId())
                .map(subscriptionBox -> {
                    DTOMapper.updateSubscriptionBox(subscriptionBox, subscriptionBoxDTO);
                    return CompletableFuture.completedFuture(subscriptionBoxRepository.update(subscriptionBox));
                })
                .orElseThrow(() -> new IllegalArgumentException("Subscription isn't found"));

    }

    @Override
    @Async
    public CompletableFuture<Void> delete(String id) {
        if (id == null || id.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("ID cannot be null or empty"));
        }

        if (subscriptionBoxRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Subscription Box not found");
        }
        subscriptionBoxRepository.delete(id);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBoxDTO>> findByPriceLessThan(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceLessThan(price);
        List<SubscriptionBoxDTO> dtoResult = result.stream()
                .map(DTOMapper::convertModelToDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtoResult);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBoxDTO>> findByPriceGreaterThan(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceGreaterThan(price);
        List<SubscriptionBoxDTO> dtoResult = result.stream()
                .map(DTOMapper::convertModelToDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtoResult);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBoxDTO>> findByPriceEquals(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceEquals(price);
        List<SubscriptionBoxDTO> dtoResult = result.stream()
                .map(DTOMapper::convertModelToDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtoResult);
    }

    @Override
    @Async
    public CompletableFuture<Optional<List<SubscriptionBoxDTO>>> findByName(String name) {
        Optional<List<SubscriptionBox>> result = subscriptionBoxRepository.findByName(name);
        Optional<List<SubscriptionBoxDTO>> dtoResult = result.map(list -> list.stream()
                .map(DTOMapper::convertModelToDto)
                .collect(Collectors.toList()));
        return CompletableFuture.completedFuture(dtoResult);
    }


    @Override
    public CompletableFuture<Optional<List<String>>> findDistinctNames() {
        Optional<List<String>> distinctNames = subscriptionBoxRepository.findDistinctNames();
        return CompletableFuture.completedFuture(distinctNames);
    }


}

