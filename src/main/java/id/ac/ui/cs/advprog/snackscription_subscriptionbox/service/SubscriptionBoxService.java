package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;

public interface SubscriptionBoxService {
    public SubscriptionBox addBox(SubscriptionBox subscriptionBox);
    public SubscriptionBox editBox(String id, SubscriptionBox subscriptionBox);
    public SubscriptionBox deleteBox(String id);
    public List<SubscriptionBox> viewAll();
    public String viewDetails(String boxId);
    public List<SubscriptionBox> filterByPrice(int price);
    // public List<SubscriptionBox> filterByRating(int rating);
}