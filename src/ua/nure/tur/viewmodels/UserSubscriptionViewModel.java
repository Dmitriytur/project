package ua.nure.tur.viewmodels;

import ua.nure.tur.entities.Subscription;

public class UserSubscriptionViewModel {

    private Subscription subscription;

    private String periodicalName;

    private boolean active;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
