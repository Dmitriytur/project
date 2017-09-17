package ua.nure.tur.services;

public abstract class ServiceFactory {

    private static ServiceFactory factory;

    public static ServiceFactory getFactory() {
        return factory;
    }

    public static void setFactory(ServiceFactory factory) {
        ServiceFactory.factory = factory;
    }


    public abstract UserService getUserService();

    public abstract PeriodicalService getPeriodicalService();

    public abstract SubscriptionService getSubscriptionService();

    public abstract ReviewService getReviewService();
}