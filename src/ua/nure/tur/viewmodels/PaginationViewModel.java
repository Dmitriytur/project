package ua.nure.tur.viewmodels;

public class PaginationViewModel {

    private static final int PAGES_NUMBER = 5;

    private int currentPage;

    private int firstPage;

    private int lastPage;

    private boolean prevActive;

    private boolean nextActive;

    public PaginationViewModel(int currentPage, int itemsSize, int limit) {

        this.currentPage = currentPage;
        int maxPage = (int) Math.ceil((double) itemsSize / (double) limit);

        prevActive = currentPage != 1;
        nextActive = currentPage < maxPage;




        firstPage = currentPage - 2;
        if (firstPage < 1){
            firstPage = 1;
        }

        lastPage = firstPage + (PAGES_NUMBER - 1);
        if (lastPage > maxPage){
            lastPage = maxPage;
        }


    }


    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isPrevActive() {
        return prevActive;
    }

    public boolean isNextActive() {
        return nextActive;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }
}
