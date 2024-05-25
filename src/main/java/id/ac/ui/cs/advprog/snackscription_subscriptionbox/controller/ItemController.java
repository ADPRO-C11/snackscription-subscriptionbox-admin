package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RestController
@RequestMapping("/items")
public class ItemController {

    private final List<Item> items = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        items.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item updatedItem) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                item.setName(updatedItem.getName());
                item.setQuantity(updatedItem.getQuantity());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void setItems(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
    }

}