package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;

import java.util.List;

public interface SubscriptionBoxService {
    public SubscriptionBox create(SubscriptionBox SubscriptionBox);
    public List<SubscriptionBox> findAll();

    SubscriptionBox edit(String id);
    // Add any other service methods required by your application.
}
