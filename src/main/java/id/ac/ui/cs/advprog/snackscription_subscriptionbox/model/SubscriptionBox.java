package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class SubscriptionBox {
    private String id;
    private String name;
    private String category;
    private String photo; //Box Thumbnail Address from static folder
    private List<Integer> price; // Prices according to subscription length (1 month/3 month/ 6 month)
    private List<Items> items; // Item Model
    private Date dateCreated;
}
