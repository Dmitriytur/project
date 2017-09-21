package ua.nure.tur.viewmodels;

import ua.nure.tur.entities.Periodical;

import java.util.List;

public class SearchResultViewModel {

    private List<Periodical> periodicals;

    private int amount;

    private PaginationViewModel pagination;

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaginationViewModel getPagination() {
        return pagination;
    }

    public void setPagination(PaginationViewModel pagination) {
        this.pagination = pagination;
    }
}
