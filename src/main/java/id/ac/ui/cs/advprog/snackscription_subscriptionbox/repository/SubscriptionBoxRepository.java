package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class SubscriptionBoxRepository {

    private List<SubscriptionBox> subscriptionBoxes = new ArrayList<>();
    private List<SubscriptionBox> filteredBoxesByPrice = new ArrayList<>();
    private List<SubscriptionBox> filteredBoxesByRating = new ArrayList<>();

    public SubscriptionBox addBox(SubscriptionBox box) {
        subscriptionBoxes.add(box);
        return box;
    }

    public SubscriptionBox deleteBox(String id) {
        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
            if (subscriptionBox.getId().equals(id)) {
                subscriptionBoxes.remove(subscriptionBox);
                return subscriptionBox;
            }
        }
        return null;
    }

    public SubscriptionBox editBox(String id, SubscriptionBox box) {
        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
            if (subscriptionBox.getId().equals(id)) {
                subscriptionBox = box;
                return subscriptionBox;
            }
        }
        return null;
    }

    public List<SubscriptionBox> viewAll() {
        return subscriptionBoxes;
    }

    public String viewDetails(String boxId) {
        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
            if (subscriptionBox.getId().equals(boxId)) {
                return subscriptionBox.getName();
            }
        }
        return null;
    }

    public List<SubscriptionBox> filterByPrice(int price) {
        List<SubscriptionBox> filteredBoxes = new ArrayList<>();
        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
            if (subscriptionBox.getPrice() == price) {
                filteredBoxes.add(subscriptionBox);
            }
        }
        filteredBoxesByPrice = filteredBoxes;
        return filteredBoxesByPrice;
    }
}