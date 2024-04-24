package id.ac.ui.cs.advprog.snackscription.service;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Items;
import java.util.List;

public interface ItemsService {
    public Items create(Items items);
    public List<Items> findAll();
}
