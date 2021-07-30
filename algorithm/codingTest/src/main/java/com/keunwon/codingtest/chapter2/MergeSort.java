package com.keunwon.codingtest.chapter2;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public List<Integer> mergeSplitFunc(List<Integer> dataList) {
        if (dataList.size() <= 1) {
            return dataList;
        }

        int medium = dataList.size() / 2;
        List<Integer> leftList = mergeSplitFunc(dataList.subList(0, medium));
        List<Integer> rightList = mergeSplitFunc(dataList.subList(medium, dataList.size()));

        return mergeFunc(leftList, rightList);
    }

    public List<Integer> mergeFunc(List<Integer> leftList, List<Integer> rightList) {
        List<Integer> mergeList = new ArrayList<>();
        int leftPoint = 0;
        int rightPoint = 0;

        while (leftList.size() > leftPoint && rightList.size() > rightPoint) {
            if (leftList.get(leftPoint) > rightList.get(rightPoint)) {
                mergeList.add(rightList.get(rightPoint));
                rightPoint++;
                continue;
            }

            mergeList.add(leftList.get(leftPoint));
            leftPoint++;
        }

        while (leftList.size() > leftPoint) {
            mergeList.add(leftList.get(leftPoint));
            leftPoint++;
        }

        while (rightList.size() > rightPoint) {
            mergeList.add(rightList.get(rightPoint));
            rightPoint++;
        }

        return mergeList;
    }
}
