package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory.SubscriptionBoxFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DTOMapperTest {

    @Test
    void testConvertModelToDto() {
        Item item1 = new Item("1", "Item 1", 10);
        Item item2 = new Item("2", "Item 2", 20);
        List<Item> items = List.of(item1, item2);

        SubscriptionBox model = new SubscriptionBox( "Basic",  "Monthly", 100, items, "Description");
        SubscriptionBoxDTO dto = DTOMapper.convertModelToDto(model);

        assertThat(dto.getId()).isEqualTo(model.getId());
        assertThat(dto.getName()).isEqualTo("Basic");
        assertThat(dto.getType()).isEqualTo("MONTHLY");
        assertThat(dto.getPrice()).isEqualTo(100);
        assertThat(dto.getItems()).hasSize(2);
        assertThat(dto.getDescription()).isEqualTo("Description");
    }

    @Test
    void testConvertDTOtoModel() {
        ItemDTO item1 = new ItemDTO("1", "Item 1", 10);
        ItemDTO item2 = new ItemDTO("2", "Item 2", 20);
        List<ItemDTO> items = List.of(item1, item2);

        SubscriptionBoxDTO dto = new SubscriptionBoxDTO("1","Basic", "Monthly", 100, items, "Description");
        SubscriptionBox model = DTOMapper.convertDTOtoModel(dto);
        assertThat(model.getName()).isEqualTo("Basic");
        assertThat(model.getType()).isEqualTo("MONTHLY");
        assertThat(model.getPrice()).isEqualTo(100);
        assertThat(model.getItems()).hasSize(2);
        assertThat(model.getDescription()).isEqualTo("Description");
    }

    @Test
    void testUpdateSubscriptionBox() {
        SubscriptionBox model = new SubscriptionBox("1", "Monthly", 100, null, "Old Description");
        ItemDTO item1 = new ItemDTO("1", "Item 1", 10);
        List<ItemDTO> items = List.of(item1);

        SubscriptionBoxDTO dto = new SubscriptionBoxDTO("1", "Basic", "Monthly", 150, items, "New Description");
        SubscriptionBox updatedModel = DTOMapper.updateSubscriptionBox(model, dto);

        assertThat(updatedModel.getPrice()).isEqualTo(150);
        assertThat(updatedModel.getDescription()).isEqualTo("New Description");
        assertThat(updatedModel.getItems()).hasSize(1);
    }

    @Test
    void testConvertItemToDto() {
        Item item = new Item("1", "Item 1", 10);
        ItemDTO dto = DTOMapper.convertItemToDto(item);

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getName()).isEqualTo("Item 1");
        assertThat(dto.getQuantity()).isEqualTo(10);
    }

    @Test
    void testConvertDtoToItem() {
        ItemDTO dto = new ItemDTO("1", "Item 1", 10);
        Item item = DTOMapper.convertDtoToItem(dto);

        assertThat(item.getId()).isEqualTo("1");
        assertThat(item.getName()).isEqualTo("Item 1");
        assertThat(item.getQuantity()).isEqualTo(10);
    }

    @Test
    void testUpdateItem() {
        Item item = new Item("1", "Old Item", 5);
        ItemDTO dto = new ItemDTO("1", "New Item", 10);
        Item updatedItem = DTOMapper.updateItem(item, dto);

        assertThat(updatedItem.getName()).isEqualTo("New Item");
        assertThat(updatedItem.getQuantity()).isEqualTo(10);
    }
}
