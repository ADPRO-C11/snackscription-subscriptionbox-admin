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

        return box;
    }

    public SubscriptionBox deleteBox(String id) {

        return null;
    }

    public SubscriptionBox editBox(String id, SubscriptionBox box) {

        return null;
    }

    public List<SubscriptionBox> viewAll() {
        return subscriptionBoxes;
    }

    public String viewDetails(String boxId) {

        return null;
    }

    public List<SubscriptionBox> filterByPrice(int price) {
        return null;}
}