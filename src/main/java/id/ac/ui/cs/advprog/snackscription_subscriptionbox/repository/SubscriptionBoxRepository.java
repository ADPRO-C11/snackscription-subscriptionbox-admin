package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;


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
public class SubscriptionBoxRepository {
    @Autowired
    private EntityManager entityManager;


    @Transactional
    public SubscriptionBox save(SubscriptionBox subscriptionBox) {
        validateSubscriptionBox(subscriptionBox);

        List<Item> attachedItems = attachItems(subscriptionBox.getItems());
        subscriptionBox.setItems(attachedItems);

        entityManager.persist(subscriptionBox);
        return subscriptionBox;
    }

    private void validateSubscriptionBox(SubscriptionBox subscriptionBox) {
        if (hasThreeSimilarNames(subscriptionBox.getName())) {
            throw new IllegalArgumentException("Cannot save subscription box: more than 3 subscription boxes with similar names already exist.");
        }
        if (existsByNameAndType(subscriptionBox.getName(), subscriptionBox.getType())) {
            throw new IllegalArgumentException("Cannot save subscription box: a subscription box with the same name and type already exists.");
        }
    }

    private List<Item> attachItems(List<Item> items) {
        List<Item> attachedItems = new ArrayList<>();
        for (Item item : items) {
            Item existingItem = entityManager.find(Item.class, item.getId());
            if (existingItem != null) {
                attachedItems.add(existingItem);
            } else {
                entityManager.persist(item);
                attachedItems.add(item);
            }
        }
        return attachedItems;
    }


    private boolean hasThreeSimilarNames(String name) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE LOWER(sb.name) LIKE LOWER(:name)";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("name", "%" + name + "%");
        List<SubscriptionBox> result = query.getResultList();
        return result.size() >= 3;
    }

    private boolean existsByNameAndType(String name, String type) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE LOWER(sb.name) = LOWER(:name) AND LOWER(sb.type) = LOWER(:type)";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("name", name);
        query.setParameter("type", type);
        List<SubscriptionBox> result = query.getResultList();
        return !result.isEmpty();
    }

    @Transactional
    public Optional<SubscriptionBox> findById(String id) {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items WHERE sb.id = :id";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
    }

    @Transactional
    public List<SubscriptionBox> findAll() {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        return query.getResultList();
    }

    @Transactional
    public SubscriptionBox update(SubscriptionBox subscriptionBox) {
        return entityManager.merge(subscriptionBox);
    }
    @Transactional
    public void delete(String id) {
        SubscriptionBox subscriptionBox = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription Box ID not found"));

        // Clear the associations with items
        subscriptionBox.getItems().clear();
        entityManager.flush();

        // Now remove the subscription box
        entityManager.remove(subscriptionBox);
    }

    @Transactional
    public List<SubscriptionBox> findByPriceLessThan(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items WHERE sb.price < :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public List<SubscriptionBox> findByPriceGreaterThan(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items WHERE sb.price > :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public List<SubscriptionBox> findByPriceEquals(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items WHERE sb.price = :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public Optional<List<SubscriptionBox>> findByName(String name) {
        String jpql = "SELECT sb FROM SubscriptionBox sb LEFT JOIN FETCH sb.items WHERE LOWER(sb.name) LIKE LOWER(:name)";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("name", "%" + name.toLowerCase() + "%"); // Convert input name to lowercase
        List<SubscriptionBox> result = query.getResultList();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    @Transactional
    public Optional<List<String>> findDistinctNames() {
        String jpql = "SELECT DISTINCT sb.name FROM SubscriptionBox sb";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        List<String> result = query.getResultList();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }


}