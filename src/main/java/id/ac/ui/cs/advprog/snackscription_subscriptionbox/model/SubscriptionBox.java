package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;


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
    @JsonManagedReference
    List<Item> items;
    // Rating rating;

    public SubscriptionBox(){
        this.id = UUID.randomUUID().toString();
    }

    public SubscriptionBox( String name, String type, int price, List<Item> items){
        this.id = UUID.randomUUID().toString();
        this.setName(name);
        this.setType(type);
        this.setPrice(price);
        this.items = items;
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("monthly") |
                type.equalsIgnoreCase("quarterly") |
                type.equalsIgnoreCase("semi-annual")
        ){
            this.type = type.toUpperCase();
        }
        else{
            throw new IllegalArgumentException("Invalid type");
        }


    }
    public void setName(String name) {
        if (!name.contains("-")
        ){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Do not put '-' inside your name!");
        }


    }
    public void setPrice(int price) {
        if (price >0){
            this.price = price;
        }
        else{
            throw new IllegalArgumentException("Invalid Price, Please enter integer above 0");
        }


    }
}
;
