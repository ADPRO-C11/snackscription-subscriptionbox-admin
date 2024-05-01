package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Builder
@Getter
public class SubscriptionBox {
    String id;
    String name;
    String type;
    int price;
    List<Items> items;

    public SubscriptionBox(String name, String type, int price, List<Items> items) {

    }
}