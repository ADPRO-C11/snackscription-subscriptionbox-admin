package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.ItemDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.ItemService;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*") // Change to s
public class ItemController {
    private final JWTUtils jwtUtils;
    private final ItemService itemService;

    public ItemController(ItemService itemService, JWTUtils jwtUtils) {
        this.itemService = itemService;
        this.jwtUtils = jwtUtils;
    }

    private void validateAdminOnly(String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtUtils.extractRole(jwt).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no permission.");
        }
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<ItemDTO>>> findAll(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {

        return itemService.findAll()
                .thenApply(ResponseEntity::ok);
    }
}
