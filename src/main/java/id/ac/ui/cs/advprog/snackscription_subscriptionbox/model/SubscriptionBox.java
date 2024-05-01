package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter @Setter
public class SubscriptionBox {

    String id;
    String name;
    String type;
    int price;
    List<Items> items;


}