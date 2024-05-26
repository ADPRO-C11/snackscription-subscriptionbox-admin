package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ItemRepository {
    private List<Item> items = new ArrayList<>();
    public Item createItem(Item item) {
        items.add(item);
        return item;
    }

    public Item getItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
    public Item deleteItem(Item item) {
        items.remove(item);
        return item;
    }
    public Item editItem(Item item) {
        for (Item itemToEdit : items) {
            if (item.getId().equals(item.getId())) {
                itemToEdit = item;
                return itemToEdit;
            }
        }
        return null;
    }
}


