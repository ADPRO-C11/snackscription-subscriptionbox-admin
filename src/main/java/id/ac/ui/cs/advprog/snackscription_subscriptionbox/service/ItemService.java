package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import java.util.List;

public interface ItemService {
    Item createItem(String id, String name, int quantity);
    Item getItemById(String id);
    Item deleteItem(String id);
    Item editItem(String id, String name, int quantity);
    List<Item> getAllItems();

}
