package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

public class ItemsFactory {
    public static Items createItems(String type) {
        if (type.equalsIgnoreCase("snack")) {
            return new Snack();
        } else if (type.equalsIgnoreCase("drink")) {
            return new Drink();
        }
        return null;
    }
}
