package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SubscriptionBoxService {
    CompletableFuture<SubscriptionBox> save(SubscriptionBox subscriptionBox);

    CompletableFuture<Optional<SubscriptionBox>> findById(String id);

    CompletableFuture<List<SubscriptionBox>> findAll();

    CompletableFuture<SubscriptionBox> update(SubscriptionBox subscriptionBox);

    CompletableFuture<Void> delete(String id);

    CompletableFuture<List<SubscriptionBox>> findByPriceLessThan(int price);

    CompletableFuture<List<SubscriptionBox>> findByPriceGreaterThan(int price);

    CompletableFuture<List<SubscriptionBox>> findByPriceEquals(int price);

    CompletableFuture<Optional<List<SubscriptionBox>>> findByName(String name);

    CompletableFuture<Optional<List<String>>> findDistinctNames();



//    public SubscriptionBox addBox(SubscriptionBox subscriptionBox);
//    public SubscriptionBox editBox(String id, SubscriptionBox subscriptionBox);
//    public SubscriptionBox deleteBox(String id);
//    public List<SubscriptionBox> viewAll();
//    public String viewDetails(String boxId);
//    public List<SubscriptionBox> filterByPrice(int price);
//    // public List<SubscriptionBox> filterByRating(int rating);
}