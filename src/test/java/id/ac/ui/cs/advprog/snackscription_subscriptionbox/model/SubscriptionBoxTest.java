package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;


    public class SubscriptionBoxTests {

        private SubscriptionBox box;
        private List<Integer> validPrices;
        private List<Items> items;

        @BeforeEach
        public void setup() {
            // Initialize common test data
            validPrices = Arrays.asList(100, 270, 480);
            items = Arrays.asList(new Items(), new Items());
            box = new SubscriptionBox("1", "ChocoBox", "Food", "url/to/photo", validPrices, items, LocalDate.now());
        }

        @Test
        public void testCreateSubscriptionBox_Valid() {
            assertNotNull(box.getId());
            assertEquals("ChocoBox", box.getName());
            assertEquals("Food", box.getCategory());
            assertEquals(3, box.getPrices().size());
            assertEquals(2, box.getItems().size());
            assertNotNull(box.getDateCreated());
        }

        @Test
        public void testSubscriptionBox_NegativePrices() {
            List<Integer> negativePrices = Arrays.asList(100, -270, 480);
            assertThrows(IllegalArgumentException.class, () -> {
                new SubscriptionBox("1", "ChocoBox", "Food", "url/to/photo", negativePrices, items, LocalDate.now());
            });
        }

        @Test
        public void testSubscriptionBox_InvalidPriceCount() {
            List<Integer> twoPrices = Arrays.asList(100, 270); // Only two prices
            assertThrows(IllegalArgumentException.class, () -> {
                new SubscriptionBox("1", "ChocoBox", "Food", "url/to/photo", twoPrices, items, LocalDate.now());
            });
        }

        // Additional tests can also utilize the setup data
    }


