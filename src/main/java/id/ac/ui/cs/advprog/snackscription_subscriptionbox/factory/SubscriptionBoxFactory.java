package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Items;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionBoxFactory extends Factory<SubscriptionBox> {
    @Override
    public SubscriptionBox create(){
        return new SubscriptionBox();
    }

    public SubscriptionBox create(String id, String name, String category, String photo, List<Integer> prices, List<Items> items, LocalDate dateCreated){
        return new SubscriptionBox(id,name, category, photo,prices, items, dateCreated);
    }
}