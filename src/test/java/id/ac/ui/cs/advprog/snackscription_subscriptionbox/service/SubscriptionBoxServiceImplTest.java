package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionBoxServiceImplTest {

    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @InjectMocks
    private SubscriptionBoxServiceImpl subscriptionBoxService;

    private SubscriptionBox subscriptionBox;

    @BeforeEach
    void setUp() {
        subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null);
        subscriptionBox.setId("1");
    }

    @Test
    void testSave() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.save(subscriptionBox)).thenReturn(subscriptionBox);

        CompletableFuture<SubscriptionBox> future = subscriptionBoxService.save(subscriptionBox);
        SubscriptionBox result = future.get();

        assertEquals(subscriptionBox, result);
        verify(subscriptionBoxRepository, times(1)).save(subscriptionBox);
    }

    @Test
    void testFindById() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.of(subscriptionBox));

        CompletableFuture<Optional<SubscriptionBox>> future = subscriptionBoxService.findById("1");
        Optional<SubscriptionBox> result = future.get();

        assertTrue(result.isPresent());
        assertEquals(subscriptionBox, result.get());
        verify(subscriptionBoxRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            subscriptionBoxService.findById("").get();
        });
        verify(subscriptionBoxRepository, never()).findById(anyString());
    }

    @Test
    void testFindAll() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);
        when(subscriptionBoxRepository.findAll()).thenReturn(subscriptionBoxes);

        CompletableFuture<List<SubscriptionBox>> future = subscriptionBoxService.findAll();
        List<SubscriptionBox> result = future.get();

        assertEquals(1, result.size());
        assertEquals(subscriptionBox, result.get(0));
        verify(subscriptionBoxRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.update(subscriptionBox)).thenReturn(subscriptionBox);

        CompletableFuture<SubscriptionBox> future = subscriptionBoxService.update(subscriptionBox);
        SubscriptionBox result = future.get();

        assertEquals(subscriptionBox, result);
        verify(subscriptionBoxRepository, times(1)).update(subscriptionBox);
    }

    @Test
    void testUpdateInvalidBox() {
        assertThrows(IllegalArgumentException.class, () -> {
            subscriptionBoxService.update(null).get();
        });
        verify(subscriptionBoxRepository, never()).update(any());
    }

    @Test
    void testDelete() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.of(subscriptionBox));
        doNothing().when(subscriptionBoxRepository).delete("1");

        CompletableFuture<Void> future = subscriptionBoxService.delete("1");
        future.get();

        verify(subscriptionBoxRepository, times(1)).findById("1");
        verify(subscriptionBoxRepository, times(1)).delete("1");
    }

    @Test
    void testDeleteInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            subscriptionBoxService.delete("").get();
        });
        verify(subscriptionBoxRepository, never()).delete(anyString());
    }

    @Test
    void testDeleteSubscriptionNotFound() {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            subscriptionBoxService.delete("1").get();
        });

        verify(subscriptionBoxRepository, times(1)).findById("1");
        verify(subscriptionBoxRepository, never()).delete(anyString());
    }

    @Test
    void testFindByPriceLessThan() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);
        when(subscriptionBoxRepository.findByPriceLessThan(150)).thenReturn(subscriptionBoxes);

        CompletableFuture<List<SubscriptionBox>> future = subscriptionBoxService.findByPriceLessThan(150);
        List<SubscriptionBox> result = future.get();

        assertEquals(1, result.size());
        assertEquals(subscriptionBox, result.get(0));
        verify(subscriptionBoxRepository, times(1)).findByPriceLessThan(150);
    }

    @Test
    void testFindByPriceGreaterThan() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);
        when(subscriptionBoxRepository.findByPriceGreaterThan(50)).thenReturn(subscriptionBoxes);

        CompletableFuture<List<SubscriptionBox>> future = subscriptionBoxService.findByPriceGreaterThan(50);
        List<SubscriptionBox> result = future.get();

        assertEquals(1, result.size());
        assertEquals(subscriptionBox, result.get(0));
        verify(subscriptionBoxRepository, times(1)).findByPriceGreaterThan(50);
    }

    @Test
    void testFindByPriceEquals() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);
        when(subscriptionBoxRepository.findByPriceEquals(100)).thenReturn(subscriptionBoxes);

        CompletableFuture<List<SubscriptionBox>> future = subscriptionBoxService.findByPriceEquals(100);
        List<SubscriptionBox> result = future.get();

        assertEquals(1, result.size());
        assertEquals(subscriptionBox, result.get(0));
        verify(subscriptionBoxRepository, times(1)).findByPriceEquals(100);
    }

    @Test
    void testFindByName() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Arrays.asList(subscriptionBox);
        when(subscriptionBoxRepository.findByName("Basic")).thenReturn(Optional.of(subscriptionBoxes));

        CompletableFuture<Optional<List<SubscriptionBox>>> future = subscriptionBoxService.findByName("Basic");
        Optional<List<SubscriptionBox>> result = future.get();

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals(subscriptionBox, result.get().get(0));
        verify(subscriptionBoxRepository, times(1)).findByName("Basic");
    }

    @Test
    void testFindDistinctNames() throws ExecutionException, InterruptedException {
        List<String> names = Arrays.asList("Basic", "Premium");
        when(subscriptionBoxRepository.findDistinctNames()).thenReturn(Optional.of(names));

        CompletableFuture<Optional<List<String>>> future = subscriptionBoxService.findDistinctNames();
        Optional<List<String>> result = future.get();

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertTrue(result.get().contains("Basic"));
        assertTrue(result.get().contains("Premium"));
        verify(subscriptionBoxRepository, times(1)).findDistinctNames();
    }
}
