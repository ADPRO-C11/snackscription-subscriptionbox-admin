package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService{
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Override
    public SubscriptionBox addBox(SubscriptionBox box) {
        return subscriptionBoxRepository.addBox(box);
    }

    @Override
    public SubscriptionBox deleteBox(String id) {
        return subscriptionBoxRepository.deleteBox(id);
    }

    @Override
    public SubscriptionBox editBox(String id, SubscriptionBox box) {
        return subscriptionBoxRepository.editBox(id, box);
    }

    @Override
    public List<SubscriptionBox> viewAll() {
        return subscriptionBoxRepository.viewAll();
    }

    @Override
    public String viewDetails(String boxId) {
        return subscriptionBoxRepository.viewDetails(boxId);
    }

    @Override
    public List<SubscriptionBox> filterByPrice(int price) {
        return subscriptionBoxRepository.filterByPrice(price);
    }


}