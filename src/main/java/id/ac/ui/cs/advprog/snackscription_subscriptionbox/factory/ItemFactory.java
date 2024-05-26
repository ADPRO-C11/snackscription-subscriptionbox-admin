package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;

import java.util.List;

public class ItemFactory implements Factory<Item> {
    @Override
    public Item create(){
        return new Item();
    }

    public Item create(String id, String name, int quantity){
        return  new Item(id, name, quantity);
    }

}



