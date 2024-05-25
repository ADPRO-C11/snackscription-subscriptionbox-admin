package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    public void testCreateItem() {
        Item item = new Item("1", "Snack A", 5);
        Item createdItem = itemRepository.createItem(item);
        assertEquals(item, createdItem);
    }

    @Test
    public void testGetItemById() {
        Item item = new Item("1", "Snack A", 5);
        itemRepository.createItem(item);
        Item retrievedItem = itemRepository.getItemById("1");
        assertEquals(item, retrievedItem);
    }

    @Test
    public void testDeleteItem() {
        Item item = new Item("1", "Snack A", 5);
        itemRepository.createItem(item);
        Item deletedItem = itemRepository.deleteItem(item);
        assertEquals(item, deletedItem);
        assertNull(itemRepository.getItemById("1"));
    }

    @Test
    public void testEditItem() {
        Item item = new Item("1", "Snack A", 5);
        itemRepository.createItem(item);

        Item updatedItem = new Item("1", "Snack B", 10);
        Item editedItem = itemRepository.editItem(updatedItem);
        assertEquals(updatedItem, editedItem);

        Item retrievedItem = itemRepository.getItemById("1");
        assertEquals("Snack B", retrievedItem.getName());
        assertEquals(10, retrievedItem.getQuantity());
    }
}

