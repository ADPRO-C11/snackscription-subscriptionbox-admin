package id.ac.ui.cs.advprog.snackscription_subscriptionbox.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionBoxDTOTest {

    @Test
    void testNoArgsConstructor() {
        SubscriptionBoxDTO dto = new SubscriptionBoxDTO();
        assertThat(dto).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        ItemDTO item1 = new ItemDTO("1", "Item 1", 10);
        ItemDTO item2 = new ItemDTO("2", "Item 2", 20);
        List<ItemDTO> items = List.of(item1, item2);

        SubscriptionBoxDTO dto = new SubscriptionBoxDTO("1", "Basic", "Monthly", 100, items, "Description");

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getName()).isEqualTo("Basic");
        assertThat(dto.getType()).isEqualTo("Monthly");
        assertThat(dto.getPrice()).isEqualTo(100);
        assertThat(dto.getItems()).isEqualTo(items);
        assertThat(dto.getDescription()).isEqualTo("Description");
    }

    @Test
    void testSettersAndGetters() {
        SubscriptionBoxDTO dto = new SubscriptionBoxDTO();

        dto.setId("1");
        dto.setName("Basic");
        dto.setType("Monthly");
        dto.setPrice(100);
        ItemDTO item = new ItemDTO("1", "Item 1", 10);
        dto.setItems(List.of(item));
        dto.setDescription("Description");

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getName()).isEqualTo("Basic");
        assertThat(dto.getType()).isEqualTo("Monthly");
        assertThat(dto.getPrice()).isEqualTo(100);
        assertThat(dto.getItems()).containsExactly(item);
        assertThat(dto.getDescription()).isEqualTo("Description");
    }
}
