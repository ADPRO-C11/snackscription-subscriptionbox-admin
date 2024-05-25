package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
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
    private SubscriptionBoxDTO subscriptionBoxDTO;

    @BeforeEach
    void setUp() {
        subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null, "this is good yas");
        subscriptionBox.setId("1");

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
    void testDeleteSubscriptionNotFound() {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            subscriptionBoxService.delete("1").get();
        });

        verify(subscriptionBoxRepository, times(1)).findById("1");
        verify(subscriptionBoxRepository, never()).delete(anyString());
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
