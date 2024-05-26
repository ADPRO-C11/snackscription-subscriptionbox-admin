package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;
import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.ItemDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ItemService {
    public CompletableFuture<List<ItemDTO>> findAll();
}
