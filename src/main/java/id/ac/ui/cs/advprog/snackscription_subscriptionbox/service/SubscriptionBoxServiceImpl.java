package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;



import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;
   @Autowired
    private LogRepository logRepository;
    @Override
    @Async
    public CompletableFuture<SubscriptionBox> save(SubscriptionBoxDTO subscriptionBoxDTO) {
        try{
            SubscriptionBox subscriptionBox = DTOMapper.convertDTOtoModel(subscriptionBoxDTO);
            return CompletableFuture.completedFuture(subscriptionBoxRepository.save(subscriptionBox));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Request", e);
        }
    }

    @Override
    @Async
    public CompletableFuture<Optional<SubscriptionBox>> findById(String id) {
        Optional<SubscriptionBox> subscriptionBox = subscriptionBoxRepository.findById(id);
        return CompletableFuture.completedFuture(subscriptionBox);
    }


    @Override
    @Async
    public CompletableFuture<List<SubscriptionBoxDTO>> findAll() {
        List<SubscriptionBox> subscriptionBoxes = subscriptionBoxRepository.findAll();
        List<SubscriptionBoxDTO> dtos = subscriptionBoxes.stream()
                .map(DTOMapper::convertModelToDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtos);
    }

    @Override
    @Async
    public CompletableFuture<SubscriptionBox> update(SubscriptionBoxDTO subscriptionBoxDTO) {
        if (subscriptionBoxDTO == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        return subscriptionBoxRepository.findById(subscriptionBoxDTO.getId())
                .map(subscriptionBox -> {
                    DTOMapper.updateSubscriptionBox(subscriptionBox, subscriptionBoxDTO);
                    subscriptionBox = subscriptionBoxRepository.update(subscriptionBox);
                    return CompletableFuture.completedFuture(subscriptionBox);
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
        CompletableFuture.runAsync(() -> logUpdateStatus(id, "DELETE"));
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


    public void logUpdateStatus(String id, String status) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String logString = "Berhasil melakukan" +status + "terhadap Subscription Box dengan ID" + id + " pada " + date;

        LogAdmin log = new LogAdmin(logString, id);

        logRepository.save(log);
    }
    @Async
    public CompletableFuture<List<LogAdmin>> getLog() {
        return logRepository.findAllByOrderByDateDesc();
    }



}

