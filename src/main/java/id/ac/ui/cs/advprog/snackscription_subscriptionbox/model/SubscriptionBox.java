package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Getter @Setter
@Table(name = "subscriptionbox")
public class SubscriptionBox {
    @Id
    String id;
    @Column(name = "box_name")
    String name;

    @Column(name = "box_type")
    String type;

    @Column(name = "box_price")
    int price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscriptionbox_item",
            joinColumns = @JoinColumn(name = "subscriptionbox_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    List<Item> items;
    // Rating rating;
}