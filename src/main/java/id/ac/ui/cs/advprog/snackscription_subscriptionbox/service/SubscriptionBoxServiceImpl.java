package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller.SubscriptionBoxController;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {

    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    public SubscriptionBox create(SubscriptionBox SubscriptionBox) {
        subscriptionBoxRepository.create(SubscriptionBox);
        return SubscriptionBox;
    }

    public List<SubscriptionBox> findAll() {
        Iterator<SubscriptionBox> SubscriptionBoxIterator = subscriptionBoxRepository.findAll();
        List<SubscriptionBox> allSubscriptionBox = new ArrayList<>();
        SubscriptionBoxIterator.forEachRemaining(allSubscriptionBox::add);
        return allSubscriptionBox;
    }

    @Override
    public SubscriptionBox edit(String id){
        return null;
    }
}