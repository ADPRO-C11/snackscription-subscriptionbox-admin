package id.ac.ui.cs.advprog.snackscription_subscriptionbox.factory;

import id.ac.ui.cs.advprog.snackscription_subscriptionbox.model.SubscriptionBox;

public abstract class Factory<S> {
    public abstract SubscriptionBox create();
}
