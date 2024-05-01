package id.ac.ui.cs.advprog.snackscription_subscriptionbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.service.SubscriptionBoxService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class SubscriptionBoxControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubscriptionBoxService subscriptionBoxService;

    @InjectMocks
    private SubscriptionBoxController subscriptionBoxController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionBoxController).build();
    }

    @Test
    public void testCreateSubscriptionBox() throws Exception {
        SubscriptionBox subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId(UUID.randomUUID().toString());
        subscriptionBox.setName("Test Subscription Box");
        subscriptionBox.setPrice(100000);
        // subscriptionBox.setRating(5);

        given(subscriptionBoxService.addBox(any(SubscriptionBox.class))).willReturn(subscriptionBox);

        mockMvc.perform(post("/subscription-box/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriptionBox)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(subscriptionBox.getId()))
                .andExpect(jsonPath("$.name").value(subscriptionBox.getName()))
                .andExpect(jsonPath("$.price").value(subscriptionBox.getPrice()));
    }

    @Test
    public void testEditSubscriptionBox() throws Exception {
        SubscriptionBox subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId(UUID.randomUUID().toString());
        subscriptionBox.setName("Test Subscription Box");
        subscriptionBox.setPrice(100000);
        // subscriptionBox.setRating(5);

        given(subscriptionBoxService.editBox(any(String.class), any(SubscriptionBox.class))).willReturn(subscriptionBox);

        mockMvc.perform(get("/subscription-box/edit/" + subscriptionBox.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(subscriptionBox.getId()))
                .andExpect(jsonPath("$.name").value(subscriptionBox.getName()))
                .andExpect(jsonPath("$.price").value(subscriptionBox.getPrice()))
        // .andExpect(jsonPath("$.rating").value(subscriptionBox.getRating()))
        ;
    }

    @Test
    public void testDeleteSubscriptionBox() throws Exception {
        SubscriptionBox subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId(UUID.randomUUID().toString());
        subscriptionBox.setName("Test Subscription Box");
        subscriptionBox.setPrice(100000);
        // subscriptionBox.setRating(5);

        given(subscriptionBoxService.deleteBox(any(String.class))).willReturn(subscriptionBox);

        mockMvc.perform(get("/subscription-box/delete/" + subscriptionBox.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(subscriptionBox.getId()))
                .andExpect(jsonPath("$.name").value(subscriptionBox.getName()))
                .andExpect(jsonPath("$.price").value(subscriptionBox.getPrice()))
        // .andExpect(jsonPath("$.rating").value(subscriptionBox.getRating()))
        ;
    }

    @Test
    public void testViewSubscriptionBox() throws Exception {
        SubscriptionBox subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId(UUID.randomUUID().toString());
        subscriptionBox.setName("Test Subscription Box");
        subscriptionBox.setPrice(100000);
        // subscriptionBox.setRating(5);

        given(subscriptionBoxService.viewDetails(any(String.class))).willReturn(subscriptionBox.toString());

        mockMvc.perform(get("/subscription-box/view/" + subscriptionBox.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(subscriptionBox.toString()));
    }

    @Test
    public void testViewAllSubscriptionBox() throws Exception {
        mockMvc.perform(get("/subscription-box/viewAll/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFilterByPrice() throws Exception {
        mockMvc.perform(get("/subscription-box/filterByPrice/100000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}