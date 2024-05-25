package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemTest {

    @Test
    public void testDefaultConstructor() {
        Item item = new Item();
        assertNull(item.getId());
        assertNull(item.getName());
        assertEquals(0, item.getQuantity());
    }

    @Test
    public void testParameterizedConstructor() {
        String id = "123";
        String name = "Snack A";
        int quantity = 5;

        Item item = new Item(id, name, quantity);
        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void testSettersAndGetters() {
        Item item = new Item();
        item.setId("456");
        item.setName("Snack B");
        item.setQuantity(10);

        assertEquals("456", item.getId());
        assertEquals("Snack B", item.getName());
        assertEquals(10, item.getQuantity());
    }
}
