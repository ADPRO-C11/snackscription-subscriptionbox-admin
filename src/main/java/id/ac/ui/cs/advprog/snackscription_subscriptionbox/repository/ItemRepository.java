package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Item> findAll() {
        String jpql = "SELECT i FROM Item i";
        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
        return query.getResultList();
    }

}


