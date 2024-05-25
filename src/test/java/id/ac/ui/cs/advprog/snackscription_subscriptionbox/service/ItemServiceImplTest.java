package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {

    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        ItemRepository itemRepository = new ItemRepository();
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void testCreateItem() {
        Item createdItem = itemService.createItem("1", "Item 1", 10);

        assertNotNull(createdItem);
        assertEquals("1", createdItem.getId());
        assertEquals("Item 1", createdItem.getName());
        assertEquals(10, createdItem.getQuantity());
    }

    @Test
    void testGetItemById() {
        itemService.createItem("1", "Item 1", 10);
        Item fetchedItem = itemService.getItemById("1");

        assertNotNull(fetchedItem);
        assertEquals("1", fetchedItem.getId());
        assertEquals("Item 1", fetchedItem.getName());
        assertEquals(10, fetchedItem.getQuantity());
    }

    @Test
    void testDeleteItem() {
        itemService.createItem("1", "Item 1", 10);
        Item deletedItem = itemService.deleteItem("1");

        assertNotNull(deletedItem);
        assertEquals("1", deletedItem.getId());
        assertEquals("Item 1", deletedItem.getName());
        assertEquals(10, deletedItem.getQuantity());

        assertNull(itemService.getItemById("1"));
    }

    @Test
    void testEditItem() {
        itemService.createItem("1", "Item 1", 10);
        Item editedItem = itemService.editItem("1", "Item 1 Edited", 20);

        assertNotNull(editedItem);
        assertEquals("1", editedItem.getId());
        assertEquals("Item 1 Edited", editedItem.getName());
        assertEquals(20, editedItem.getQuantity());
    }

    @Test
    void testGetAllItems() {
        itemService.createItem("1", "Item 1", 10);
        itemService.createItem("2", "Item 2", 20);

        List<Item> allItems = itemService.getAllItems();

        assertNotNull(allItems);
        assertEquals(2, allItems.size());

        Item firstItem = allItems.getFirst();
        assertEquals("1", firstItem.getId());
        assertEquals("Item 1", firstItem.getName());
        assertEquals(10, firstItem.getQuantity());

        Item secondItem = allItems.get(1);
        assertEquals("2", secondItem.getId());
        assertEquals("Item 2", secondItem.getName());
        assertEquals(20, secondItem.getQuantity());
    }
}
