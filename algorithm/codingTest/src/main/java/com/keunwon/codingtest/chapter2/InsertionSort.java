package com.keunwon.codingtest.chapter2;

import java.util.Collections;
import java.util.List;

public class InsertionSort {

    public List<Integer> sort(List<Integer> dataList) {
        for (int i = 0; i < dataList.size() - 1; i++) {

            for (int j = i + 1; j > 0; j--) {
                if (dataList.get(j - 1) > dataList.get(j)) {
                    Collections.swap(dataList, j - 1, j);
                    continue;
                }
                break;
            }
            System.out.println(dataList);
        }
        return dataList;
    }
}
