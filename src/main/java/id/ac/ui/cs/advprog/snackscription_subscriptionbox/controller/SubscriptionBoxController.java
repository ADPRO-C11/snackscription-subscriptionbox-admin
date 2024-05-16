package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/subscription-box")
@CrossOrigin(origins = "http://localhost:3000") // Change to specific origin if needed
public class SubscriptionBoxController {

    private final SubscriptionBoxService subscriptionBoxService;

    @Autowired
    public SubscriptionBoxController(SubscriptionBoxService subscriptionBoxService) {
        this.subscriptionBoxService = subscriptionBoxService;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> createSubscriptionBox(@RequestBody SubscriptionBox subscriptionBox) {
        return subscriptionBoxService.save(subscriptionBox)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findAll() {
        return subscriptionBoxService.findAll()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> findById(@PathVariable String id) {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return subscriptionBoxService.findById(id)
                .thenApply(optionalSubscriptionBox ->
                        optionalSubscriptionBox.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build()));
    }

    @PatchMapping("/update")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> updateSubscriptionBox(@RequestBody SubscriptionBox subscriptionBox) {
        if (subscriptionBox.getId() == null || subscriptionBox.getId().isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return subscriptionBoxService.findById(subscriptionBox.getId())
                .thenCompose(optionalSubscriptionBox -> {
                    if (optionalSubscriptionBox.isEmpty()) {
                        return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
                    } else {
                        return subscriptionBoxService.update(subscriptionBox)
                                .thenApply(ResponseEntity::ok);
                    }
                });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> deleteSubscriptionBox(@PathVariable String id) {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return subscriptionBoxService.delete(id)
                .thenApply(result -> ResponseEntity.ok("DELETE SUCCESS"))
                .exceptionally(ex -> ResponseEntity.notFound().build());
    }

    @GetMapping("/price/less-than/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findByPriceLessThan(@PathVariable int price) {
        return subscriptionBoxService.findByPriceLessThan(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/price/greater-than/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findByPriceGreaterThan(@PathVariable int price) {
        return subscriptionBoxService.findByPriceGreaterThan(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/price/equals/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findByPriceEquals(@PathVariable int price) {
        return subscriptionBoxService.findByPriceEquals(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/name/{name}")
    public CompletableFuture<ResponseEntity<Optional<List<SubscriptionBox>>>> findByName(@PathVariable String name) {
        return subscriptionBoxService.findByName(name)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/distinct-names")
    public CompletableFuture<ResponseEntity<Optional<List<String>>>> findDistinctNames() {
        return subscriptionBoxService.findDistinctNames()
                .thenApply(ResponseEntity::ok);
    }
}
