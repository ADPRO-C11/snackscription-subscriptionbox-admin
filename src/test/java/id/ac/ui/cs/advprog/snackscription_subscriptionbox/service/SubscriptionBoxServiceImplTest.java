package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SubscriptionBoxServiceImplTest {

    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @InjectMocks
    private SubscriptionBoxServiceImpl subscriptionBoxService;

    private SubscriptionBox box1, box2;
    private Item item1, item2;

    @BeforeEach
    void setUp() {
        // Set up SubscriptionBox and Items
        item1 = new Item();
        item1.setName("Chocolate Bar");


        item2 = new Item();
        item2.setName("Face Mask");

        List<Item> items1 = new ArrayList<>();
        items1.add(item1);
        List<Item> items2 = new ArrayList<>();
        items2.add(item2);

        box1 = new SubscriptionBox();
        box1.setId("1");
        box1.setName("Basic Box");
        box1.setType("MONTHLY");
        box1.setPrice(100);
        box1.setItems(items1);

        box2 = new SubscriptionBox();
        box2.setId("2");
        box2.setName("Premium Box");
        box2.setType("QUARTERLY");
        box2.setPrice(200);
        box2.setItems(items2);
    }

    @Test
    public void testAddBox() {
        when(subscriptionBoxRepository.addBox(box1)).thenReturn(box1);
        SubscriptionBox result = subscriptionBoxService.addBox(box1);
        assertEquals(box1, result);
        verify(subscriptionBoxRepository).addBox(box1);
    }

    @Test
    public void testEditBox() {
        SubscriptionBox updatedBox = new SubscriptionBox();
        updatedBox.setId("1");
        updatedBox.setName("Updated Basic Box");
        updatedBox.setType("Monthly");
        updatedBox.setPrice(150);
        updatedBox.setItems(new ArrayList<>(box1.getItems()));

        when(subscriptionBoxRepository.editBox("1", updatedBox)).thenReturn(updatedBox);
        SubscriptionBox result = subscriptionBoxService.editBox("1", updatedBox);
        assertEquals("Updated Basic Box", result.getName());
        assertEquals(150, result.getPrice());
        verify(subscriptionBoxRepository).editBox("1", updatedBox);
    }

    @Test
    public void testDeleteBox() {
        when(subscriptionBoxRepository.deleteBox("1")).thenReturn(box1);
        SubscriptionBox result = subscriptionBoxService.deleteBox("1");
        assertEquals(box1, result);
        verify(subscriptionBoxRepository).deleteBox("1");
    }

    @Test
    public void testViewAll() {
        List<SubscriptionBox> boxes = new ArrayList<>();
        boxes.add(box1);
        boxes.add(box2);
        when(subscriptionBoxRepository.viewAll()).thenReturn(boxes);
        List<SubscriptionBox> result = subscriptionBoxService.viewAll();
        assertEquals(2, result.size());
        verify(subscriptionBoxRepository).viewAll();
    }

    @Test
    public void testViewDetails() {
        when(subscriptionBoxRepository.viewDetails("1")).thenReturn("Basic Box");
        String result = subscriptionBoxService.viewDetails("1");
        assertEquals("Basic Box", result);
        verify(subscriptionBoxRepository).viewDetails("1");
    }

    @Test
    public void testFilterByPrice() {
        List<SubscriptionBox> filteredBoxes = new ArrayList<>();
        filteredBoxes.add(box1);
        when(subscriptionBoxRepository.filterByPrice(100)).thenReturn(filteredBoxes);
        List<SubscriptionBox> result = subscriptionBoxService.filterByPrice(100);
        assertEquals(1, result.size());
        assertTrue(result.contains(box1));
        verify(subscriptionBoxRepository).filterByPrice(100);
    }
}
