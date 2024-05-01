package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.builder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;

public class SubscriptionBoxBuilder {
    private SubscriptionBox subscriptionBox;

    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    public SubscriptionBoxBuilder() {
        this.subscriptionBox = new SubscriptionBox();
    }

    public SubscriptionBoxBuilder setName(String name) {
        subscriptionBox.setName(name);
        return this;
    }

    public SubscriptionBoxBuilder setType(String type) {
        subscriptionBox.setType(type);
        return this;
    }

    public SubscriptionBoxBuilder setPrice(int price) {
        subscriptionBox.setPrice(price);
        return this;
    }

    public SubscriptionBoxBuilder setItems() {
        subscriptionBox.setItems(new ArrayList<>());
        return this;
    }

    public SubscriptionBoxBuilder setId(String id) {
        subscriptionBox.setId(id);
        return this;
    }

}