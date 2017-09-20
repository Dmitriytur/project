package ua.nure.tur.viewmodels;

import ua.nure.tur.entities.Periodical;
import ua.nure.tur.entities.Review;

import java.util.List;
import java.util.Map;

public class PeriodicalDetailsViewModel {

    private Periodical periodical;

    private String periodicity;

    private List<Review> reviews;

    private String category;

    private List<Periodical> similarPeriodicals;

    private Map<Integer, Double> reviewStatistic;

}
