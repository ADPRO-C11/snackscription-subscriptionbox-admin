package id.ac.ui.cs.advprog.snackscription.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Items;
public class ItemsRepositoryTest {

    private ItemsRepository itemsRepository;

    @BeforeEach
    public void setUp() {
        itemRepository = new ItemRepository();
    }

    @Test
    public void testSaveItem() {
        // Create a new item
        Items item = new Item();
        item.setName("Test Item");
        item.setQuantity(10);
        item.setId("1");

        // Save the item to the repository
        Item savedItem = itemsRepository.createItem(item);

        // Verify that the item is saved successfully
        assertNotNull(savedItem.getId());
        assertEquals("Test Item", savedItem.getName());
        assertEquals(10.0, savedItem.getQuantity());
    }    

    @Test
    public void testFindItemById() {
        // Create a new item
        Item item = new Item();
        item.setName("Test Item");
        item.setQuantity(10);
        item.setId("1");

        // Save the item to the repository
        Item savedItem = itemRepository.createItem(item);

        // Find the item by its ID
        Item foundItem = itemRepository.getItemById(savedItem.getId());

        // Verify that the item is found successfully
        assertNotNull(foundItem);
        assertEquals(savedItem.getId(), foundItem.getId());
        assertEquals("Test Item", foundItem.getName());
        assertEquals(10.0, foundItem.getQuantity());
    }

    @Test
    public void testDeleteItem() {
        // Create a new item
        Item item = new Item();
        item.setName("Test Item");
        item.setQuantity(10);
        item.setId("1");

        // Save the item to the repository
        Item savedItem = itemRepository.createItem(item);

        // Delete the item from the repository
        Item deletedItem = itemRepository.deleteItem(savedItem);

        // Verify that the item is deleted successfully
        assertNotNull(deletedItem);
        assertEquals(savedItem.getId(), deletedItem.getId());
        assertEquals("Test Item", deletedItem.getName());
        assertEquals(10.0, deletedItem.getQuantity());
    }

    @Test
    public void testEditItem() {
        // Create a new item
        Item item = new Item();
        item.setName("Test Item");
        item.setQuantity(10);
        item.setId("1");

        // Save the item to the repository
        Item savedItem = itemRepository.createItem(item);

        // Edit the item
        savedItem.setName("Edited Item");
        savedItem.setQuantity(20);

        // Edit the item in the repository
        Item editedItem = itemRepository.editItem(savedItem);

        // Verify that the item is edited successfully
        assertNotNull(editedItem);
        assertEquals(savedItem.getId(), editedItem.getId());
        assertEquals("Edited Item", editedItem.getName());
        assertEquals(20.0, editedItem.getQuantity());
    }

    @Test
    public void testGetItems() {
        // Create a new item
        Item item = new Item();
        item.setName("Test Item");
        item.setQuantity(10);

        // Save the item to the repository
        Item savedItem = itemsRepository.createItem(item);

        // Get all items from the repository
        List<Item> items = itemsRepository.getItems();

        // Verify that the item is in the list of items
        assertTrue(items.contains(savedItem));
    }

}    