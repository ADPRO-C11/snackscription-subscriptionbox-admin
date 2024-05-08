package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.builder;

import org.springframework.stereotype.Component;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;

@Component
public class ItemBuilder {
    private Item item;

    private ItemBuilder() {
        item = new Item();
    }

    public Item setName(String name) {
        item.setName(name);
        return item;
    }

    public Item setQuantity(int quantity) {
        item.setQuantity(quantity);
        return item;
    }

    public Item setId(String id) {
        item.setId(id);
        return item;
    }

}
