package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.springframework.stereotype.Component;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory.SubscriptionBoxFactory;

import java.util.List;
import java.util.Optional;

@Component
public class DTOMapper {
    public static SubscriptionBoxDTO convertModeltoDto (SubscriptionBox subscriptionBox){
        return new SubscriptionBoxDTO(
                subscriptionBox.getId(),
                subscriptionBox.getName(),
                subscriptionBox.getType(),
                subscriptionBox.getPrice(),
                subscriptionBox.getItems()
        );
    }

    public static SubscriptionBox convertDTOtoModel(SubscriptionBoxDTO subscriptionBoxDTO){
        String id = subscriptionBoxDTO.getId();
        String name = subscriptionBoxDTO.getName();
        String type = subscriptionBoxDTO.getType();
        int price = subscriptionBoxDTO.getPrice();
        List<Item> items = subscriptionBoxDTO.getItems();
        return new SubscriptionBoxFactory().create(id,name,type,price,items);
    }

    public static SubscriptionBox updateSubscriptionBox(SubscriptionBox subscriptionBox, SubscriptionBoxDTO subscriptionBoxDTO){
        Optional.ofNullable(subscriptionBoxDTO.getItems()).ifPresent(subscriptionBox::setItems);
        Optional.of(
                subscriptionBoxDTO.getPrice()).ifPresent(subscriptionBox::setPrice);
        return subscriptionBox;

    }
}
