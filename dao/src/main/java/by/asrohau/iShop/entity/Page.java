package by.asrohau.iShop.entity;

import by.asrohau.iShop.dao.util.DAOFinals;

public class Page {

    private int currentPage;
    private int maxPage;
    private int row;

    public Page(){}

    public Page(String currentPage, long countItems){
        int _MAX_ROWS_AT_PAGE = DAOFinals.MAX_ROWS_AT_PAGE;
        this.currentPage = Integer.parseInt(currentPage);
        this.maxPage = calculateMaxPage(countItems, _MAX_ROWS_AT_PAGE);
        this.row = calculateRow(this.currentPage, _MAX_ROWS_AT_PAGE);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getRow() {
        return row;
    }

    private int calculateMaxPage(long countItems, int _MAX_ROWS_AT_PAGE){
        return (int) Math.ceil(((double) countItems) / _MAX_ROWS_AT_PAGE);
    }

    private int calculateRow(int currentPage, int _MAX_ROWS_AT_PAGE) {
        return (currentPage - 1) * _MAX_ROWS_AT_PAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (currentPage != page.currentPage) return false;
        if (maxPage != page.maxPage) return false;
        return row == page.row;
    }

    @Override
    public int hashCode() {
        int result = currentPage;
        result = 31 * result + maxPage;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", maxPage=" + maxPage +
                ", row=" + row +
                '}';
    }
}
