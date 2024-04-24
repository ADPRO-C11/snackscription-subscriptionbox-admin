package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Items {
    private String itemsId;
    private String itemsName;
    private int itemsQuantity;
    private double price;

    private List<ItemObserver> observers = new ArrayList<>();

    public void addObserver(ItemObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ItemObserver observer : observers) {
            observer.update(this);
        }
    }


}