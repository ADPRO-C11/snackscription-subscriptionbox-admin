package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table (name = "Item")
public class Item {
    @Id
    private String id;
    @Column(name = "item_name")
    private String name;

    @Column(name = "item_quantity")
    private int quantity;

    @ManyToMany(mappedBy = "items")
    private List<SubscriptionBox> subscriptionBoxes;
}