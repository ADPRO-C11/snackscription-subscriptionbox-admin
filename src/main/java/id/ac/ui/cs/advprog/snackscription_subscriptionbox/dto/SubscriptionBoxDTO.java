package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import lombok.*;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SubscriptionBoxDTO {
    String id;
    String name;
    String type;
    int price;
    List<Item> items;
}
