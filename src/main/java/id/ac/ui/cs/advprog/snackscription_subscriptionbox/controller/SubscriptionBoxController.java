package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
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
@CrossOrigin(origins = "*") // Change to specific origin if needed
public class SubscriptionBoxController {

    private final SubscriptionBoxService subscriptionBoxService;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionBoxController.class);

    @Autowired
    public SubscriptionBoxController(SubscriptionBoxService subscriptionBoxService) {
        this.subscriptionBoxService = subscriptionBoxService;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> createSubscriptionBox(@RequestBody SubscriptionBoxDTO subscriptionBoxDTO) {
        SubscriptionBox subscriptionBox = DTOMapper.convertDTOtoModel(subscriptionBoxDTO);
        return subscriptionBoxService.save(subscriptionBox)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findAll() {
        return subscriptionBoxService.findAll()
                .thenApply(ResponseEntity::ok);
    }

    @PatchMapping("/update")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> updateSubscriptionBox(@RequestBody SubscriptionBoxDTO subscriptionBoxDTO) {
        if (subscriptionBoxDTO.getId() == null || subscriptionBoxDTO.getId().isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }

        return subscriptionBoxService.findById(subscriptionBoxDTO.getId())
                .thenCompose(optionalSubscriptionBox -> {
                    if (optionalSubscriptionBox.isEmpty()) {
                        return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
                    } else {
                        return subscriptionBoxService.update(subscriptionBoxDTO)
                                .thenApply(ResponseEntity::ok);
                    }
                });
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<SubscriptionBoxDTO>> findById(@PathVariable String id) {
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
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceLessThan(@PathVariable int price) {
        return subscriptionBoxService.findByPriceLessThan(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/price/greater-than/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceGreaterThan(@PathVariable int price) {
        return subscriptionBoxService.findByPriceGreaterThan(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/price/equals/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceEquals(@PathVariable int price) {
        return subscriptionBoxService.findByPriceEquals(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/name/{nameURL}")
    public CompletableFuture<ResponseEntity<Optional<List<SubscriptionBoxDTO>>>> findByName(@PathVariable String nameURL) {
//        logger.info("Searching for SubscriptionBox with name before split: {}", nameURL);
        String name = nameURL.replaceAll("-", " ");
//        logger.info("Searching for SubscriptionBox with name: {}", name);
        return subscriptionBoxService.findByName(name)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/distinct-names")
    public CompletableFuture<ResponseEntity<Optional<List<String>>>> findDistinctNames() {
        return subscriptionBoxService.findDistinctNames()
                .thenApply(ResponseEntity::ok);
    }
}
