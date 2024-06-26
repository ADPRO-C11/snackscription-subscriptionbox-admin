package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;

import java.util.List;

public interface Factory <T> {
    T create();

    T create( String name, String type, int price, List<Item> items , String description);
}