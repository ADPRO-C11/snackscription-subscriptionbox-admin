package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.DTOMapper;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto.SubscriptionBoxDTO;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxService;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.utils.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriptionBoxControllerTest {

    @Mock
    private SubscriptionBoxService subscriptionBoxService;

    @InjectMocks
    private SubscriptionBoxController subscriptionBoxController;
    @Mock
    private JWTUtils jwtUtils;
    private SubscriptionBox subscriptionBox;
    private SubscriptionBoxDTO subscriptionBoxDTO;

    private final String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MTY2MjAzOTksImV4cCI6MTcxNjcwNjc5OX0.dFmE18NL6H1my8Dki1Lp4DlwGIRbTTpgj3qUFKBoBoo";
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create items
        Item item1 = new Item("1", "Item 1", 10);
        Item item2 = new Item("2", "Item 2", 20);
        List<Item> items = Arrays.asList(item1, item2);

        // Create subscription box with items
        subscriptionBox = new SubscriptionBox("Basic", "Monthly", 100, items, "this is good yas");
        subscriptionBox.setId(UUID.randomUUID().toString());

        // Convert to DTO
        subscriptionBoxDTO = DTOMapper.convertModelToDto(subscriptionBox);
        when(jwtUtils.isTokenValid(validToken)).thenReturn(true);
        when(jwtUtils.extractRole(any(String.class))).thenReturn("admin");
    }

    @Test
    void testCreateSubscriptionBox_HappyPath()throws IllegalAccessException  {
        when(subscriptionBoxService.save(any(SubscriptionBoxDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBox));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.createSubscriptionBox(validToken, subscriptionBoxDTO);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBox), result.join());
    }

    @Test
    void testCreateSubscriptionBox_UnhappyPath()throws IllegalAccessException {
        when(subscriptionBoxService.save(any(SubscriptionBoxDTO.class)))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error saving subscription box")));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.createSubscriptionBox(validToken, subscriptionBoxDTO);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.badRequest().build(), result.join());
    }

    @Test
    void testFindAll_HappyPath() throws IllegalAccessException {
        List<SubscriptionBox> subscriptionBoxes = Collections.singletonList(subscriptionBox);

        when(subscriptionBoxService.findAll())
                .thenReturn(CompletableFuture.completedFuture(subscriptionBoxes));

        CompletableFuture<ResponseEntity<List<SubscriptionBox>>> result = subscriptionBoxController.findAll(validToken);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxes), result.join());
    }


    @Test
    void testUpdateSubscriptionBox_HappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findById(subscriptionBoxDTO.getId()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(subscriptionBoxDTO)));
        when(subscriptionBoxService.update(any(SubscriptionBoxDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(subscriptionBox));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.updateSubscriptionBox(validToken, subscriptionBoxDTO);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBox), result.join());
    }

    @Test
    void testUpdateSubscriptionBox_UnhappyPath() throws IllegalAccessException {
        subscriptionBoxDTO.setId(null);

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.updateSubscriptionBox(validToken, subscriptionBoxDTO);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.badRequest().build(), result.join());
    }

    @Test
    void testUpdateSubscriptionBox_NotFound()throws IllegalAccessException  {
        when(subscriptionBoxService.findById(subscriptionBoxDTO.getId()))
                .thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        CompletableFuture<ResponseEntity<SubscriptionBox>> result = subscriptionBoxController.updateSubscriptionBox(validToken, subscriptionBoxDTO);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.notFound().build(), result.join());
    }

    @Test
    void testFindById_HappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findById(subscriptionBox.getId()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(subscriptionBoxDTO)));

        CompletableFuture<ResponseEntity<SubscriptionBoxDTO>> result = subscriptionBoxController.findById(validToken, subscriptionBox.getId());

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(subscriptionBoxDTO), result.join());
    }

    @Test
    void testFindById_UnhappyPath()throws IllegalAccessException  {
        String invalidId = "invalid-uuid";
        CompletableFuture<ResponseEntity<SubscriptionBoxDTO>> result = subscriptionBoxController.findById(validToken, invalidId);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.badRequest().build(), result.join());
    }

    @Test
    void testDeleteSubscriptionBox_HappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.delete(subscriptionBox.getId()))
                .thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<ResponseEntity<String>> result = subscriptionBoxController.deleteSubscriptionBox(validToken, subscriptionBox.getId());

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok("DELETE SUCCESS"), result.join());
    }

    @Test
    void testDeleteSubscriptionBox_UnhappyPath() throws IllegalAccessException {
        CompletableFuture<ResponseEntity<String>> expectedResult = CompletableFuture.completedFuture(ResponseEntity.badRequest().build());

        CompletableFuture<ResponseEntity<String>> result = subscriptionBoxController.deleteSubscriptionBox(validToken, "invalid Id");

        assertTrue(result.isDone());
        assertEquals(expectedResult.join(), result.join());
    }


    @Test
    void testFindByPriceLessThan_HappyPath() throws IllegalAccessException {
        List<SubscriptionBoxDTO> expectedDTOs = Collections.singletonList(subscriptionBoxDTO);
        when(subscriptionBoxService.findByPriceLessThan(150))
                .thenReturn(CompletableFuture.completedFuture(expectedDTOs));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceLessThan(validToken, 150);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(expectedDTOs), result.join());
    }

    @Test
    void testFindByPriceLessThan_UnhappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findByPriceLessThan(150))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error finding by price less than")));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceLessThan(validToken, 150);

        assertNotNull(result);
        assertTrue(result.isDone());
    }

    @Test
    void testFindByPriceGreaterThan_HappyPath() throws IllegalAccessException {
        List<SubscriptionBoxDTO> expectedDTOs = Collections.singletonList(subscriptionBoxDTO);
        when(subscriptionBoxService.findByPriceGreaterThan(50))
                .thenReturn(CompletableFuture.completedFuture(expectedDTOs));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceGreaterThan(validToken, 50);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(expectedDTOs), result.join());
    }

    @Test
    void testFindByPriceGreaterThan_UnhappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findByPriceGreaterThan(50))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error finding by price greater than")));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceGreaterThan(validToken, 50);

        assertNotNull(result);
        assertTrue(result.isDone());
    }

    @Test
    void testFindByPriceEquals_HappyPath() throws IllegalAccessException {
        List<SubscriptionBoxDTO> expectedDTOs = Collections.singletonList(subscriptionBoxDTO);
        when(subscriptionBoxService.findByPriceEquals(100))
                .thenReturn(CompletableFuture.completedFuture(expectedDTOs));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceEquals(validToken, 100);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(expectedDTOs), result.join());
    }

    @Test
    void testFindByPriceEquals_UnhappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findByPriceEquals(100))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error finding by price equals")));

        CompletableFuture<ResponseEntity<List<SubscriptionBoxDTO>>> result = subscriptionBoxController.findByPriceEquals(validToken, 100);

        assertNotNull(result);
        assertTrue(result.isDone());

    }

    @Test
    void testFindByName_HappyPath() throws IllegalAccessException {
        List<SubscriptionBoxDTO> expectedDTOs = Collections.singletonList(subscriptionBoxDTO);
        String nameURL = "Basic".replaceAll(" ", "-");
        when(subscriptionBoxService.findByName(subscriptionBox.getName()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(expectedDTOs)));

        CompletableFuture<ResponseEntity<Optional<List<SubscriptionBoxDTO>>>> result = subscriptionBoxController.findByName(validToken, nameURL);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(Optional.of(expectedDTOs)), result.join());
    }

    @Test
    void testFindByName_UnhappyPath() throws IllegalAccessException {
        String nameURL = "Basic".replaceAll(" ", "-");
        when(subscriptionBoxService.findByName(subscriptionBox.getName()))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error finding by name")));

        CompletableFuture<ResponseEntity<Optional<List<SubscriptionBoxDTO>>>> result = subscriptionBoxController.findByName(validToken, nameURL);

        assertNotNull(result);
        assertTrue(result.isDone());
    }

    @Test
    void testFindDistinctNames_HappyPath() throws IllegalAccessException {
        List<String> names = Arrays.asList("Basic", "Premium");

        when(subscriptionBoxService.findDistinctNames())
                .thenReturn(CompletableFuture.completedFuture(Optional.of(names)));

        CompletableFuture<ResponseEntity<Optional<List<String>>>> result = subscriptionBoxController.findDistinctNames(validToken);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(Optional.of(names)), result.join());
    }

    @Test
    void testFindDistinctNames_UnhappyPath() throws IllegalAccessException {
        when(subscriptionBoxService.findDistinctNames())
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Error finding distinct names")));

        CompletableFuture<ResponseEntity<Optional<List<String>>>> result = subscriptionBoxController.findDistinctNames(validToken);

        assertNotNull(result);
        assertTrue(result.isDone());
    }
}
