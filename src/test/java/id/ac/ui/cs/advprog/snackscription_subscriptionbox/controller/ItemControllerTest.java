package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemControllerTest {

    private ItemController itemController;
    private List<Item> items;

    @BeforeEach
    public void setUp() {
        itemController = new ItemController();
        items = new ArrayList<>();
        items.add(new Item("1", "Snack A", 5));
        items.add(new Item("2", "Snack B", 10));
        itemController.setItems(items);
    }

    @Test
    public void testGetAllItems() {
        ResponseEntity<List<Item>> responseEntity = itemController.getAllItems();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(items, responseEntity.getBody());
    }

    @Test
    public void testGetItemById() {
        ResponseEntity<Item> responseEntity = itemController.getItemById("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Snack A", Objects.requireNonNull(responseEntity.getBody()).getName());
    }

    @Test
    public void testGetItemById_NotFound() {
        ResponseEntity<Item> responseEntity = itemController.getItemById("100");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateItem() {
        Item newItem = new Item("3", "Snack C", 8);
        ResponseEntity<Item> responseEntity = itemController.createItem(newItem);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newItem, responseEntity.getBody());
    }

    @Test
    public void testUpdateItem() {
        Item updatedItem = new Item("1", "New Snack A", 7);
        ResponseEntity<Item> responseEntity = itemController.updateItem("1", updatedItem);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedItem, responseEntity.getBody());
    }

    @Test
    public void testUpdateItem_NotFound() {
        Item updatedItem = new Item("100", "New Snack A", 7);
        ResponseEntity<Item> responseEntity = itemController.updateItem("100", updatedItem);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteItem() {
        ResponseEntity<Void> responseEntity = itemController.deleteItem("1");
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(1, itemController.getItems().size());
    }

    @Test
    public void testDeleteItem_NotFound() {
        ResponseEntity<Void> responseEntity = itemController.deleteItem("100");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
