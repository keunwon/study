package com.keunwon.codingtest.chapter2;

import java.util.Collections;
import java.util.List;

public class BubbleSort {

    public List<Integer> sort(List<Integer> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            boolean isSwap = false;

            for (int j = 0; j < dataList.size() - 1 - i; j++) {
                if (dataList.get(j) > dataList.get(j + 1)) {
                    Collections.swap(dataList, j, j + 1);
                    isSwap = true;
                }
            }

            if (!isSwap) { break; }
        }
        return dataList;
    }
}
