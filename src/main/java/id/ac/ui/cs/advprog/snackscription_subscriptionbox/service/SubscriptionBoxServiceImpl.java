package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;



import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Optional<SubscriptionBox>> findById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return CompletableFuture.completedFuture(subscriptionBoxRepository.findById(id));
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> findAll() {
        List<SubscriptionBox> subscriptionBoxes = subscriptionBoxRepository.findAll();
        return CompletableFuture.completedFuture(subscriptionBoxes);
    }

    @Override
    @Async
    public CompletableFuture<SubscriptionBox> update(SubscriptionBox subscriptionBox) {
        if (subscriptionBox == null) {
            throw new IllegalArgumentException("SubscriptionBox cannot be null");
        }
        return CompletableFuture.completedFuture(subscriptionBoxRepository.update(subscriptionBox));
    }

    @Override
    @Async
    public CompletableFuture<Void> delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        Optional<SubscriptionBox> subscriptionBox = subscriptionBoxRepository.findById(id);
        if (!subscriptionBox.isPresent()) {
            throw new IllegalArgumentException("Subscription Box not found");
        }
        subscriptionBoxRepository.delete(id);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> findByPriceLessThan(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceLessThan(price);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> findByPriceGreaterThan(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceGreaterThan(price);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> findByPriceEquals(int price) {
        List<SubscriptionBox> result = subscriptionBoxRepository.findByPriceEquals(price);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<Optional<List<SubscriptionBox>>> findByName(String name) {
        Optional<List<SubscriptionBox>> result = subscriptionBoxRepository.findByName(name);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<Optional<List<String>>> findDistinctNames() {
        Optional<List<String>> distinctNames = subscriptionBoxRepository.findDistinctNames();
        return CompletableFuture.completedFuture(distinctNames);
    }


}

//
//@Service
//public class SubscriptionBoxServiceImpl implements SubscriptionBoxService{
//    @Autowired
//    private SubscriptionBoxRepository subscriptionBoxRepository;
//
//    @Override
//    @Async
//    public CompletableFuture<SubscriptionBox>
//
//    @Override
//    public SubscriptionBox addBox(SubscriptionBox box) {
//        return subscriptionBoxRepository.addBox(box);
//    }
//
//    @Override
//    public SubscriptionBox deleteBox(String id) {
//        return subscriptionBoxRepository.deleteBox(id);
//    }
//
//    @Override
//    public SubscriptionBox editBox(String id, SubscriptionBox box) {
//        return subscriptionBoxRepository.editBox(id, box);
//    }
//
//    @Override
//    public List<SubscriptionBox> viewAll() {
//        return subscriptionBoxRepository.viewAll();
//    }
//
//    @Override
//    public String viewDetails(String boxId) {
//        return subscriptionBoxRepository.viewDetails(boxId);
//    }
//
//    @Override
//    public List<SubscriptionBox> filterByPrice(int price) {
//        return subscriptionBoxRepository.filterByPrice(price);
//    }
//
//
//}