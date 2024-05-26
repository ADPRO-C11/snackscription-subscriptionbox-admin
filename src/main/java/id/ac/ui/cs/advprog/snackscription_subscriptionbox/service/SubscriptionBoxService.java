package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SubscriptionBoxService {
    CompletableFuture<SubscriptionBox> save(SubscriptionBoxDTO subscriptionBoxDTO);

    CompletableFuture<Optional<SubscriptionBox>> findById(String id);

    CompletableFuture<List<SubscriptionBoxDTO>> findAll();

    CompletableFuture<SubscriptionBox> update(SubscriptionBoxDTO subscriptionBoxDTO);

    CompletableFuture<Void> delete(String id);

    CompletableFuture<List<SubscriptionBoxDTO>> findByPriceLessThan(int price);

    CompletableFuture<List<SubscriptionBoxDTO>> findByPriceGreaterThan(int price);

    CompletableFuture<List<SubscriptionBoxDTO>> findByPriceEquals(int price);

    CompletableFuture<Optional<List<SubscriptionBoxDTO>>> findByName(String name);

    CompletableFuture<Optional<List<String>>> findDistinctNames();
    CompletableFuture<List<LogAdmin>> getLog();


}