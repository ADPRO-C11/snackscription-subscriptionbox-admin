package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;

import java.util.List;

public class SubscriptionBoxFactory implements Factory<SubscriptionBox> {
    @Override
    public SubscriptionBox create(){
        return new SubscriptionBox();
    }

    public SubscriptionBox create(String id, String name, String type, int price, List<Item> items ){
        return  new SubscriptionBox( name, type, price, items);
    }
}
