package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.ItemDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.ItemRepository;
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
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Async
    public CompletableFuture<List<ItemDTO>> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> dtos = items.stream()
                .map(DTOMapper::convertItemToDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtos);
    }

}
