package id.ac.ui.cs.advprog.snackscription_subscriptionbox.repository;


import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;
import jakarta.transaction.TransactionScoped;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionBoxRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public SubscriptionBox save(SubscriptionBox subscriptionBox) {
        entityManager.persist(subscriptionBox);
        return subscriptionBox;
    }

    @Transactional
    public Optional<SubscriptionBox> findById(String id){
        SubscriptionBox subscription = entityManager.find(SubscriptionBox.class, id);
        return Optional.ofNullable(subscription);
    }
    @Transactional
    public List<SubscriptionBox> findAll(){
        String jpql = "SELECT sb FROM SubscriptionBox sb";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        return query.getResultList();
    }

    @Transactional
    public SubscriptionBox update(SubscriptionBox subscriptionBox){
        return entityManager.merge(subscriptionBox);
    }

    @Transactional
    public void delete(String id){
        SubscriptionBox subscription = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription Box ID not found"));
        entityManager.remove(subscription);
    }

    @Transactional
    public List<SubscriptionBox> findByPriceLessThan(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE sb.price < :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public List<SubscriptionBox> findByPriceGreaterThan(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE sb.price > :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public List<SubscriptionBox> findByPriceEquals(int price) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE sb.price = :price";
        TypedQuery<SubscriptionBox> query = entityManager.createQuery(jpql, SubscriptionBox.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Transactional
    public Optional<List<SubscriptionBox>> findByName(String name) {
        String jpql = "SELECT sb FROM SubscriptionBox sb WHERE LOWER(sb.name) LIKE LOWER(:name)";
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

//    private List<SubscriptionBox> subscriptionBoxes = new ArrayList<>();
//    private List<SubscriptionBox> filteredBoxesByPrice = new ArrayList<>();
////    private List<SubscriptionBox> filteredBoxesByRating = new ArrayList<>();
//
//    public SubscriptionBox addBox(SubscriptionBox box) {
//        subscriptionBoxes.add(box);
//        return box;
//    }
//
//    public SubscriptionBox deleteBox(String id) {
//        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
//            if (subscriptionBox.getId().equals(id)) {
//                subscriptionBoxes.remove(subscriptionBox);
//                return subscriptionBox;
//            }
//        }
//        return null;
//    }
//
//    public SubscriptionBox editBox(String id, SubscriptionBox updatedBox) {
//        for (SubscriptionBox box : subscriptionBoxes) {
//            if (box.getId().equals(id)) {
//                // Assuming the updatedBox object contains the updated fields
//                box.setName(updatedBox.getName());
//                box.setPrice(updatedBox.getPrice());
//                return box;  // Return the updated box
//            }
//        }
//        return null;  // Return null if no box with the given id was found
//    }
//
//
//    public List<SubscriptionBox> viewAll() {
//        return subscriptionBoxes;
//    }
//
//    public String viewDetails(String boxId) {
//        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
//            if (subscriptionBox.getId().equals(boxId)) {
//                return subscriptionBox.getName();
//            }
//        }
//        return null;
//    }
//
//    public List<SubscriptionBox> filterByPrice(int price) {
//        List<SubscriptionBox> filteredBoxes = new ArrayList<>();
//        for (SubscriptionBox subscriptionBox : subscriptionBoxes) {
//            if (subscriptionBox.getPrice() == price) {
//                filteredBoxes.add(subscriptionBox);
//            }
//        }
//        filteredBoxesByPrice = filteredBoxes;
//        return filteredBoxesByPrice;
//    }
}