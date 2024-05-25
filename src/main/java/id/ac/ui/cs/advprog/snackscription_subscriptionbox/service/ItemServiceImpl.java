package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(String id, String name, int quantity) {
        Item item = new Item(id, name, quantity);
        return itemRepository.createItem(item);
    }

    @Override
    public Item getItemById(String id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public Item deleteItem(String id) {
        Item item = itemRepository.getItemById(id);
        if (item != null) {
            return itemRepository.deleteItem(item);
        }
        return null;
    }

    @Override
    public Item editItem(String id, String name, int quantity) {
        Item existingItem = itemRepository.getItemById(id);
        if (existingItem != null) {
            existingItem.setName(name);
            existingItem.setQuantity(quantity);
            return itemRepository.editItem(existingItem);
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }
}

