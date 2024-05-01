package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
public class SubscriptionBoxServiceTest {

    private SubscriptionBoxService subscriptionBoxService;

    @BeforeEach
    public void setUp() {
        subscriptionBoxService = new SubscriptionBoxServiceImpl();
        SubscriptionBox box1 = new SubscriptionBox();
        box1.setId("1");
        box1.setName("Box 1");
        box1.setPrice(100000);
        box1.setType("MTH");
        subscriptionBoxService.addBox(box1);
    }

    @Test
    public void testAddBox() {
        SubscriptionBox box2 = new SubscriptionBox();
        box2.setId("2");
        box2.setName("Box 2");
        box2.setPrice(200000);
        box2.setType("MTH");
        subscriptionBoxService.addBox(box2);
        assertEquals(2, subscriptionBoxService.viewAll().size());
    }

    @Test
    public void testEditBox() {
        SubscriptionBox box1 = new SubscriptionBox();
        box1.setId("1");
        box1.setName("Box 1");
        box1.setPrice(100000);
        box1.setType("MTH");
        subscriptionBoxService.editBox("1", box1);
        assertEquals("Box 1", subscriptionBoxService.viewDetails("1"));
    }

    @Test
    public void testDeleteBox() {
        subscriptionBoxService.deleteBox("1");
        assertEquals(0, subscriptionBoxService.viewAll().size());
    }

    @Test
    public void testViewAll() {
        subscriptionBoxService.viewAll();
        assertEquals(1, subscriptionBoxService.viewAll().size());
    }

    @Test
    public void testViewDetails() {
        String name = subscriptionBoxService.viewDetails("1");
        assertEquals("Box 1", name);
    }

    @Test
    public void testFilterByPrice() {
        List<SubscriptionBox> boxes = subscriptionBoxService.filterByPrice(100000);
        assertEquals(1, boxes.size());
    }


}