package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class SubscriptionBoxRepository {
    private List<SubscriptionBox> SubscriptionBoxData = new ArrayList<>();

    public SubscriptionBox create(SubscriptionBox SubscriptionBox) {
        SubscriptionBoxData.add(SubscriptionBox);
        return SubscriptionBox;
    }

    public Iterator<SubscriptionBox> findAll() {
        return SubscriptionBoxData.iterator();
    }
    // Add more methods as needed for your application.
}
