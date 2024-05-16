package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class SubscriptionBoxTest {
    List<Item> items;
    SubscriptionBox subscriptionBox;
    @BeforeEach
    public void setUp() {
        this.subscriptionBox = new SubscriptionBox();
        this.subscriptionBox.setId("b060669f-4047-47ee-9d0f-1b4a123a104a");
        this.subscriptionBox.setName("BOX1");
        this.subscriptionBox.setType("MONTHLY");
        this.subscriptionBox.setPrice(100000);
        this.items = new ArrayList<>();
        Item item1 = new Item();

        items.add(item1);
        Item item2 = new Item();
        items.add(item2);
        this.subscriptionBox.setItems(items);
    }

    @Test
    public void testGetId() {
        assertEquals("b060669f-4047-47ee-9d0f-1b4a123a104a", subscriptionBox.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("BOX1", subscriptionBox.getName());
    }

    @Test
    public void testGetType() {
        assertEquals("MONTHLY", subscriptionBox.getType());
    }

    @Test
    public void testCreateInvalidType(){
        assertThrows(IllegalArgumentException.class, () -> {
            SubscriptionBox subscriptionBoxTest = new SubscriptionBox(subscriptionBox.getName(), "Daily", subscriptionBox.getPrice(), subscriptionBox.getItems());
        });

    }

    @Test
    public void testInvalidPrice(){
        assertThrows(IllegalArgumentException.class, () -> {
           subscriptionBox.setPrice(-1);
        });

    }
    @Test
    public void testGetPrice() {
        assertEquals(100000, subscriptionBox.getPrice());
    }

    @Test
    public void testGetItems() {
        assertEquals(2, subscriptionBox.getItems().size());
    }
}