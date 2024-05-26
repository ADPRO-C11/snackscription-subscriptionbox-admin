package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory.SubscriptionBoxFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DTOMapper {
    public static SubscriptionBoxDTO convertModelToDto(SubscriptionBox subscriptionBox) {
        List<ItemDTO> itemDTOs = Optional.ofNullable(subscriptionBox.getItems())
                .map(items -> items.stream()
                        .map(DTOMapper::convertItemToDto)
                        .collect(Collectors.toList()))
                .orElse(null);

        return new SubscriptionBoxDTO(
                subscriptionBox.getId(),
                subscriptionBox.getName(),
                subscriptionBox.getType(),
                subscriptionBox.getPrice(),
                itemDTOs,
                subscriptionBox.getDescription()
        );
    }

    public static SubscriptionBox convertDTOtoModel(SubscriptionBoxDTO subscriptionBoxDTO) {
        List<Item> items = Optional.ofNullable(subscriptionBoxDTO.getItems()).map(dtoItems ->
                dtoItems.stream()
                        .map(DTOMapper::convertDtoToItem)
                        .collect(Collectors.toList())
        ).orElse(null);

        return new SubscriptionBoxFactory().create(
                subscriptionBoxDTO.getName(),
                subscriptionBoxDTO.getType(),
                subscriptionBoxDTO.getPrice(),
                items,
                subscriptionBoxDTO.getDescription()
        );
    }

    public static SubscriptionBox updateSubscriptionBox(SubscriptionBox subscriptionBox, SubscriptionBoxDTO subscriptionBoxDTO) {
        Optional.ofNullable(subscriptionBoxDTO.getItems()).ifPresent(dtoItems -> {
            List<Item> items = dtoItems.stream()
                    .map(DTOMapper::convertDtoToItem)
                    .collect(Collectors.toList());
            subscriptionBox.setItems(items);
        });
        Optional.of(subscriptionBoxDTO.getPrice()).ifPresent(subscriptionBox::setPrice);
        Optional.ofNullable(subscriptionBoxDTO.getDescription()).ifPresent(subscriptionBox::setDescription);
        return subscriptionBox;
    }

    public static ItemDTO convertItemToDto(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getQuantity()
        );
    }

    public static Item convertDtoToItem(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getQuantity()
        );
    }

    public static Item updateItem(Item item, ItemDTO itemDTO) {
        Optional.ofNullable(itemDTO.getId()).ifPresent(item::setId);
        Optional.ofNullable(itemDTO.getName()).ifPresent(item::setName);
        Optional.of(itemDTO.getQuantity()).ifPresent(item::setQuantity);
        return item;
    }
}
