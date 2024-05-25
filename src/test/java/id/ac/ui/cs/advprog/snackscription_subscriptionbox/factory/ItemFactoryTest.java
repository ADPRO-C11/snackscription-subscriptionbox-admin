package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ItemFactoryTest {

    @Test
    public void testCreateDefaultItem() {
        ItemFactory itemFactory = new ItemFactory();
        Item item = itemFactory.create();

        assertTrue(isValidUUID(item.getId()));
        assertNull(item.getName());
        assertEquals(0, item.getQuantity());
    }

    @Test
    public void testCreateItemWithParameters() {
        ItemFactory itemFactory = new ItemFactory();
        String id = UUID.randomUUID().toString();
        String name = "Snack A";
        int quantity = 5;

        Item item = itemFactory.create(id, name, quantity);

        // Memeriksa apakah nilai parameter sesuai dengan nilai yang diatur di item
        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
    }

    private boolean isValidUUID(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

