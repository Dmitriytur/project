package ua.nure.tur.viewmodels;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.entities.Review;
import ua.nure.tur.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeriodicalDetailsViewModel {

    private static final int MAX_SCORE = 5;

    private Periodical periodical;

    private List<Review> reviews;

    private List<Periodical> similarPeriodicals;

    private Map<Long, User> userMap = new HashMap<>();


    private Map<Long, Integer> reviewStatisticPercents;

    private Map<Long, Integer> reviewStatisticAmounts;

    public Map<Long, Integer> getReviewStatisticPercents() {
        return reviewStatisticPercents;
    }

    public Map<Long, Integer> getReviewStatisticAmounts() {
        return reviewStatisticAmounts;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        countStatistic();
    }

    public List<Periodical> getSimilarPeriodicals() {
        return similarPeriodicals;
    }

    public void setSimilarPeriodicals(List<Periodical> similarPeriodicals) {
        this.similarPeriodicals = similarPeriodicals;
    }

    public int getReviewsAmount(){
        return reviews.size();
    }


    private void countStatistic(){
        reviewStatisticPercents = new HashMap<>();
        reviewStatisticAmounts = new HashMap<>();
        int[] amount = new int[MAX_SCORE + 1];
        int allScores = reviews.size();
        for (Review review: reviews){
            amount[review.getScore()]++;
        }
        for (int i = 1; i <= MAX_SCORE; i++){
            int percent = (int)(((double)amount[i]/(double) allScores) * 100);
            reviewStatisticAmounts.put((long)i, amount[i]);
            reviewStatisticPercents.put((long)i, percent);
        }

    }


    public Map<Long, User> getUserMap() {
        return userMap;
    }

    public void addUser(User user) {
        userMap.put((long)user.getId(), user);
    }
}
