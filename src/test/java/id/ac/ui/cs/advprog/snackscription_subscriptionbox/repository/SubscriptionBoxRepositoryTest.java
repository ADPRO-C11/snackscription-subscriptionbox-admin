package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;

public class SubscriptionBoxRepositoryTest {

    private SubscriptionBoxRepository subscriptionBoxRepository;

    @BeforeEach
    public void setUp() {
        subscriptionBoxRepository = new SubscriptionBoxRepository();
    }

    @Test
    public void testAddBox() {
        SubscriptionBox box = new SubscriptionBox();
        SubscriptionBox addedBox = subscriptionBoxRepository.addBox(box);
        assertNotNull(addedBox);
    }

    @Test
    public void testDeleteBox() {
        SubscriptionBox box = new SubscriptionBox();
        SubscriptionBox deletedBox = subscriptionBoxRepository.deleteBox(box);
        assertNotNull(deletedBox);
    }

    @Test
    public void testEditBox() {
        SubscriptionBox box = new SubscriptionBox();
        box.setId("1");
        String id = box.getId();
        box.setName("abc");
        subscriptionBoxRepository.addBox(box);
        box.setName("def");
        SubscriptionBox editedBox = subscriptionBoxRepository.editBox(id, box);
        assertEquals("def", editedBox.getName());
    }

    @Test
    public void testViewAll() {
        SubscriptionBox box1 = new SubscriptionBox();
        SubscriptionBox box2 = new SubscriptionBox();
        subscriptionBoxRepository.addBox(box1);
        subscriptionBoxRepository.addBox(box2);
        List<SubscriptionBox> allBoxes = subscriptionBoxRepository.viewAll();
        assertEquals(2, allBoxes.size());
    }

    @Test
    public void testViewDetails() {
        SubscriptionBox box = new SubscriptionBox();
        box.setName("abc");
        box.setId("1");
        subscriptionBoxRepository.addBox(box);
        String details = subscriptionBoxRepository.viewDetails(box.getId());
        assertEquals("abc", details);
    }

    @Test
    public void testFilterByPrice() {
        SubscriptionBox box1 = new SubscriptionBox();
        box1.setPrice(15);
        SubscriptionBox box2 = new SubscriptionBox();
        box2.setPrice(25);
        subscriptionBoxRepository.addBox(box1);
        subscriptionBoxRepository.addBox(box2);
        List<SubscriptionBox> filteredBoxes = subscriptionBoxRepository.filterByPrice(15);
        assertEquals(1, filteredBoxes.size());
    }
}