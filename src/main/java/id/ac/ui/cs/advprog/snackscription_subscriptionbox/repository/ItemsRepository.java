package id.ac.ui.cs.advprog.snackscription.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Items;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ItemsRepository {
    private List<Items> itemsData = new ArrayList<>();

    public Items create(Items items) {
        itemsData.add(items);
        return items;
    }

    public Iterator<Items> findAll() {
        return itemsData.iterator();
    }

}
