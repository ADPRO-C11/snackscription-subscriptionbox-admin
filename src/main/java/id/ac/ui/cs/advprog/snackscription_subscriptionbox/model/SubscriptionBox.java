package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Items;
import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class SubscriptionBox {
    private String id;
    private String name;
    private String category;
    private String photo;
    private List<Integer> prices;
    private List<Items> items;
    private LocalDate dateCreated;

    public SubscriptionBox(String id, String name, String category, String photo, List<Integer> prices, List<Items> items, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.photo = photo;
        if (prices.size() != 3) {
            throw new IllegalArgumentException("Prices list must contain exactly three elements");
        }
        Collectors Collectors = null;
        this.prices = prices.stream()
                .filter(price -> price > 0)
                .collect(Collectors.toList());
        if (this.prices.size() != prices.size()) {
            throw new IllegalArgumentException("Prices must be positive");
        }
        this.items = items;
        this.dateCreated = dateCreated;
    }

    // Getters and setters
}
