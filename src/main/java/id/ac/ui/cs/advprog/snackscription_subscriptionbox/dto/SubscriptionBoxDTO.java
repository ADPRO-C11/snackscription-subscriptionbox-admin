package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import lombok.*;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionBoxDTO {
    private String id;
    private String name;
    private String type;
    private int price;
    private List<ItemDTO> items;
    private String description;
}