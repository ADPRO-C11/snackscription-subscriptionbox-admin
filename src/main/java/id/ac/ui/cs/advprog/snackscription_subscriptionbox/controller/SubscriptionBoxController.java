package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.utils.JWTUtils;
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
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/subscription-box")
@CrossOrigin(origins = "*") // Change to specific origin if needed
public class SubscriptionBoxController {

    private final JWTUtils jwtUtils;
    private final SubscriptionBoxService subscriptionBoxService;

    public SubscriptionBoxController(SubscriptionBoxService subscriptionBoxService, JWTUtils jwtUtils) {
        this.subscriptionBoxService = subscriptionBoxService;
        this.jwtUtils = jwtUtils;
    }

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionBoxController.class);

    private void validateToken(String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtUtils.isTokenValid(jwt)) {
            throw new IllegalAccessException("You have no permission.");
        }
    }

    private void validateAdminOnly(String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtUtils.extractRole(jwt).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no permission.");
        }
    }

    @GetMapping("")
    public ResponseEntity<String> main(){
        return ResponseEntity.ok("Snackscription - SubscriptionBox Management API by ADMIN only!");
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> createSubscriptionBox(@RequestHeader(value = "Authorization") String token, @RequestBody SubscriptionBoxDTO subscriptionBoxDTO) throws IllegalAccessException {
        validateAdminOnly(token);
        return subscriptionBoxService.save(subscriptionBoxDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> findAll(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
        validateToken(token);
        return subscriptionBoxService.findAll()
                .thenApply(ResponseEntity::ok);
    }

    @PatchMapping("/update")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> updateSubscriptionBox(@RequestHeader(value = "Authorization") String token, @RequestBody SubscriptionBoxDTO subscriptionBoxDTO) throws IllegalAccessException {
        validateAdminOnly(token);
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
    public CompletableFuture<ResponseEntity<SubscriptionBoxDTO>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) throws IllegalAccessException {
        validateToken(token);
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
    public CompletableFuture<ResponseEntity<String>> deleteSubscriptionBox(@RequestHeader(value = "Authorization") String token, @PathVariable String id) throws IllegalAccessException {
        validateAdminOnly(token);
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
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceLessThan(@RequestHeader(value = "Authorization") String token, @PathVariable int price) throws IllegalAccessException {
        validateToken(token);
        return subscriptionBoxService.findByPriceLessThan(price)
                .thenApply(ResponseEntity::ok);
    }


    @GetMapping("/price/greater-than/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceGreaterThan(@RequestHeader(value = "Authorization") String token, @PathVariable int price) throws IllegalAccessException {
        validateToken(token);
        return subscriptionBoxService.findByPriceGreaterThan(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/price/equals/{price}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> findByPriceEquals(@RequestHeader(value = "Authorization") String token, @PathVariable int price) throws IllegalAccessException {
        validateToken(token);
        return subscriptionBoxService.findByPriceEquals(price)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/name/{nameURL}")
    public CompletableFuture<ResponseEntity<Optional<List<SubscriptionBoxDTO>>>> findByName(@RequestHeader(value = "Authorization") String token, @PathVariable String nameURL) throws IllegalAccessException {
        validateToken(token);
        String name = nameURL.replaceAll("-", " ");
        return subscriptionBoxService.findByName(name)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/distinct-names")
    public CompletableFuture<ResponseEntity<Optional<List<String>>>> findDistinctNames(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
        validateToken(token);
        return subscriptionBoxService.findDistinctNames()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/logs")
    public CompletableFuture<ResponseEntity<List<LogAdmin>>> updateSubscriptionBox(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
        validateAdminOnly(token);
        return subscriptionBoxService.getLog()
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.notFound().build());
    }
}
