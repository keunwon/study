package com.keunwon.codingtest.chapter2;

import java.util.Collections;
import java.util.List;

public class SelectorSort {

    public List<Integer> sort(List<Integer> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            int minIndex = i;

            for (int j = i + 1; j < dataList.size(); j++) {
                if (dataList.get(minIndex) > dataList.get(j)) {
                    minIndex = j;
                }
            }
            Collections.swap(dataList, minIndex, i);
        }
        return dataList;
    }
}
