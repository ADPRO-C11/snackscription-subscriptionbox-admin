package id.ac.ui.cs.advprog.snackscription_subscriptionbox.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.LogAdmin;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.LogRepository;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository.SubscriptionBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
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

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private SubscriptionBoxServiceImpl subscriptionBoxService;

    private SubscriptionBox subscriptionBox;
    private SubscriptionBoxDTO subscriptionBoxDTO;

    @BeforeEach
    void setUp() {
        subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, null, "this is good yas");
        subscriptionBox.setId("1");

        subscriptionBoxDTO = DTOMapper.convertModelToDto(subscriptionBox);
    }

    @Test
    void testFindAll() throws ExecutionException, InterruptedException {
        List<SubscriptionBox> subscriptionBoxes = Collections.singletonList(subscriptionBox);
        when(subscriptionBoxRepository.findAll()).thenReturn(subscriptionBoxes);

        CompletableFuture<List<SubscriptionBoxDTO>> future = subscriptionBoxService.findAll();
        List<SubscriptionBoxDTO> result = future.get();

        assertEquals(1, result.size());
        verify(subscriptionBoxRepository, times(1)).findAll();
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


    @Test
    void testSave() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.save(any(SubscriptionBox.class))).thenReturn(subscriptionBox);

        CompletableFuture<SubscriptionBox> future = subscriptionBoxService.save(subscriptionBoxDTO);
        SubscriptionBox result = future.get();

        assertNotNull(result);
        assertEquals(subscriptionBox, result);
        verify(subscriptionBoxRepository, times(1)).save(any(SubscriptionBox.class));
    }

    @Test
    void testFindById() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.of(subscriptionBox));

        CompletableFuture<Optional<SubscriptionBox>> future = subscriptionBoxService.findById("1");
        Optional<SubscriptionBox> result = future.get();

        assertTrue(result.isPresent());

        verify(subscriptionBoxRepository, times(1)).findById("1");
    }

    @Test
    void testUpdate() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findById("1")).thenReturn(Optional.of(subscriptionBox));
        when(subscriptionBoxRepository.update(any(SubscriptionBox.class))).thenReturn(subscriptionBox);

        CompletableFuture<SubscriptionBox> future = subscriptionBoxService.update(subscriptionBoxDTO);
        SubscriptionBox result = future.get();

        assertNotNull(result);
        assertEquals(subscriptionBox, result);
        verify(subscriptionBoxRepository, times(1)).findById("1");
        verify(subscriptionBoxRepository, times(1)).update(any(SubscriptionBox.class));
    }

    @Test
    void testFindByPriceLessThan() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findByPriceLessThan(150)).thenReturn(List.of(subscriptionBox));

        CompletableFuture<List<SubscriptionBoxDTO>> future = subscriptionBoxService.findByPriceLessThan(150);
        List<SubscriptionBoxDTO> result = future.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subscriptionBoxRepository, times(1)).findByPriceLessThan(150);
    }

    @Test
    void testFindByPriceGreaterThan() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findByPriceGreaterThan(50)).thenReturn(List.of(subscriptionBox));

        CompletableFuture<List<SubscriptionBoxDTO>> future = subscriptionBoxService.findByPriceGreaterThan(50);
        List<SubscriptionBoxDTO> result = future.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subscriptionBoxRepository, times(1)).findByPriceGreaterThan(50);
    }

    @Test
    void testFindByPriceEquals() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findByPriceEquals(100)).thenReturn(List.of(subscriptionBox));

        CompletableFuture<List<SubscriptionBoxDTO>> future = subscriptionBoxService.findByPriceEquals(100);
        List<SubscriptionBoxDTO> result = future.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subscriptionBoxRepository, times(1)).findByPriceEquals(100);
    }

    @Test
    void testFindByName() throws ExecutionException, InterruptedException {
        when(subscriptionBoxRepository.findByName("Basic")).thenReturn(Optional.of(List.of(subscriptionBox)));

        CompletableFuture<Optional<List<SubscriptionBoxDTO>>> future = subscriptionBoxService.findByName("Basic");
        Optional<List<SubscriptionBoxDTO>> result = future.get();

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        verify(subscriptionBoxRepository, times(1)).findByName("Basic");
    }

    @Test
    void testGetLog() throws ExecutionException, InterruptedException {
        LogAdmin log = new LogAdmin("Log Message", "1");
        when(logRepository.findAllByOrderByDateDesc()).thenReturn(CompletableFuture.completedFuture(List.of(log)));

        CompletableFuture<List<LogAdmin>> future = subscriptionBoxService.getLog();
        List<LogAdmin> result = future.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(log, result.get(0));
        verify(logRepository, times(1)).findAllByOrderByDateDesc();
    }

    @Test
    void testLogUpdateStatus() {
        ArgumentCaptor<LogAdmin> logCaptor = ArgumentCaptor.forClass(LogAdmin.class);

        subscriptionBoxService.logUpdateStatus("1", "UPDATE");

        verify(logRepository).save(logCaptor.capture());
        LogAdmin capturedLog = logCaptor.getValue();

        assertNotNull(capturedLog);
        assertEquals("1", capturedLog.getSubBoxId());
        assertTrue(capturedLog.getLogString().contains("UPDATE"));
    }
}
