package com.myshop.common.ui;

import lombok.Getter;

@Getter
public class Pagination {
    private int current;
    private int beginPage;
    private int endPage;
    private int numberOfPage;
    private int totalPages;

    public Pagination(int current, int numberOfPage, int totalPages) {
        this.current = current;
        this.totalPages = totalPages;
        this.numberOfPage = numberOfPage;

        int pageMode = current % 5;
        beginPage = pageMode == 0 ? current - 5 + 1 : current - pageMode + 1;
        endPage = beginPage + 5 - 1;

        if (totalPages < endPage) {
            endPage = totalPages;
        }
    }

    public boolean isHasNext() {
        return endPage < totalPages;
    }

    public int[] getPageNos() {
        int[] result = new int[endPage - beginPage + 1];
        for (int i = beginPage; i <= endPage; i++) {
            result[i - beginPage] = i;
        }
        return result;
    }
}
