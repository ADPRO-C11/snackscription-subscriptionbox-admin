package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class SubscriptionBoxFactoryTest {

    @Test
    void testCreateEmptySubscriptionBox() {
        SubscriptionBoxFactory subscriptionBoxFactory = new SubscriptionBoxFactory();
        SubscriptionBox subscriptionBox = subscriptionBoxFactory.create();
        assertNotNull(subscriptionBox.getId()); // Check if ID is auto-generated
        assertNull(subscriptionBox.getName()); // Expect null for name as none set
        assertNull(subscriptionBox.getType()); // Expect null for type as none set
        assertEquals(0, subscriptionBox.getPrice()); // Default price should be 0
        assertNull(subscriptionBox.getItems()); // Items should be null by default
    }

    @Test
    void testCreateSubscriptionBoxWithParameters() {
        SubscriptionBoxFactory subscriptionBoxFactory = new SubscriptionBoxFactory();
        List<Item> items = new ArrayList<>();
        items.add(new Item());  // Assuming you have a constructor in Item class like this
        items.add(new Item());

        SubscriptionBox subscriptionBox = subscriptionBoxFactory.create("1", "Deluxe Box", "MONTHLY", 150, items);

        assertEquals("Deluxe Box", subscriptionBox.getName());
        assertEquals("MONTHLY", subscriptionBox.getType());
        assertEquals(150, subscriptionBox.getPrice());
        assertNotNull(subscriptionBox.getItems());
        assertEquals(2, subscriptionBox.getItems().size()); // Check that two items were added
    }
}
