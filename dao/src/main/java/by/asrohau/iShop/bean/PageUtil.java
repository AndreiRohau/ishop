package by.asrohau.iShop.bean;

import by.asrohau.iShop.dao.util.DAOFinals;

public class PageUtil {

    private int currentPage;
    private int maxPage;
    private int row;

    public PageUtil(){}

    public PageUtil(String page, long countItems){
        int _MAX_ROWS_AT_PAGE = DAOFinals.MAX_ROWS_AT_PAGE;
        this.currentPage = Integer.parseInt(page);
        this.maxPage = calculateMaxPage(countItems, _MAX_ROWS_AT_PAGE);
        this.row = calculateRow(currentPage, _MAX_ROWS_AT_PAGE);
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
        return (int) Math.ceil(((double) countItems / _MAX_ROWS_AT_PAGE));
    }

    private int calculateRow(int currentPage, int _MAX_ROWS_AT_PAGE) {
        return (currentPage - 1) * _MAX_ROWS_AT_PAGE;
    }
}
