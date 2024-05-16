package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriptionBoxControllerTest {

    @Mock
    private SubscriptionBoxService subscriptionBoxService;

    @InjectMocks
    private SubscriptionBoxController subscriptionBoxController;

    private SubscriptionBox subscriptionBox;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);
        subscriptionBox.setId(UUID.randomUUID().toString());
    }

    @Test
    void testCreateSubscriptionBox() {
        when(subscriptionBoxService.save(any(SubscriptionBox.class)))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBox));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.createSubscriptionBox(subscriptionBox);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBox), result.join());
    }

    @Test
    void testFindAll() {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);

        when(subscriptionBoxService.findAll())
                .thenReturn(CompletableFuture.completedFuture(subscriptionBoxes));

        CompletableFuture<ResponseEntity<List<SubscriptionBox>>> result = subscriptionBoxController.findAll();

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxes), result.join());
    }

    @Test
    void testFindById() {
        when(subscriptionBoxService.findById(subscriptionBox.getId()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(subscriptionBox)));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.findById(subscriptionBox.getId());

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBox), result.join());
    }

    @Test
    void testFindByIdInvalidId() {
        String invalidId = "invalid_id";

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.findById(invalidId);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.badRequest().build(), result.join());
    }

    @Test
    void testUpdateSubscriptionBox() {
        when(subscriptionBoxService.findById(subscriptionBox.getId()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(subscriptionBox)));
        when(subscriptionBoxService.update(any(SubscriptionBox.class)))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBox));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.updateSubscriptionBox(subscriptionBox);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBox), result.join());
    }

    @Test
    void testDeleteSubscriptionBox() {
        when(subscriptionBoxService.delete(subscriptionBox.getId()))
                .thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<ResponseEntity<String>> result = subscriptionBoxController.deleteSubscriptionBox(subscriptionBox.getId());

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok("DELETE SUCCESS"), result.join());
    }

    @Test
    void testFindByPriceLessThan() {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);

        when(subscriptionBoxService.findByPriceLessThan(150))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBoxes));

        CompletableFuture<ResponseEntity<List<SubscriptionBox>>> result = subscriptionBoxController.findByPriceLessThan(150);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxes), result.join());
    }

    @Test
    void testFindByPriceGreaterThan() {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);

        when(subscriptionBoxService.findByPriceGreaterThan(50))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBoxes));

        CompletableFuture<ResponseEntity<List<SubscriptionBox>>> result = subscriptionBoxController.findByPriceGreaterThan(50);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxes), result.join());
    }

    @Test
    void testFindByPriceEquals() {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);

        when(subscriptionBoxService.findByPriceEquals(100))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBoxes));

        CompletableFuture<ResponseEntity<List<SubscriptionBox>>> result = subscriptionBoxController.findByPriceEquals(100);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxes), result.join());
    }

    @Test
    void testFindByName() {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);

        when(subscriptionBoxService.findByName("Basic"))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(subscriptionBoxes)));

        CompletableFuture<ResponseEntity<Optional<List<SubscriptionBox>>>> result = subscriptionBoxController.findByName("Basic");

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(Optional.of(subscriptionBoxes)), result.join());
    }

    @Test
    void testFindDistinctNames() {
        List<String> names = Arrays.asList("Basic", "Premium");

        when(subscriptionBoxService.findDistinctNames())
                .thenReturn(CompletableFuture.completedFuture(Optional.of(names)));

        CompletableFuture<ResponseEntity<Optional<List<String>>>> result = subscriptionBoxController.findDistinctNames();

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(Optional.of(names)), result.join());
    }
}
