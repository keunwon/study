package com.keunwon.codingtest.chapter2;

import java.util.List;

public class BinarySearch {

    public boolean search(List<Integer> dataList, int search) {
        if (dataList.size() == 0) {
            return false;
        }
        if (dataList.size() == 1 && dataList.get(0) == search) {
            return true;
        }
        if (dataList.size() == 1 && dataList.get(0) != search) {
            return false;
        }


        final int mid = dataList.size() / 2;
        final int midValue = dataList.get(mid);

        if (midValue == search) {
            return true;
        }
        if (midValue < search) {
            return search(dataList.subList(mid, dataList.size()), search);
        } else {
            return search(dataList.subList(0, mid), search);
        }
    }
}
