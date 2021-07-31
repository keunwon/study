package com.keunwon.codingtest.chapter2;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class QuickSort {

    public List<Integer> sort(List<Integer> dataList) {
        if (dataList.size() <= 1) { return dataList; }

        int pivot = dataList.get(0);

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (int i = 1; i < dataList.size(); i++) {
            if (pivot > dataList.get(i)) {
                leftList.add(dataList.get(i));
            } else {
                rightList.add(dataList.get(i));
            }
        }

        List<Integer> mergeList = new ArrayList<>();
        mergeList.addAll(sort(leftList));
        mergeList.addAll(singletonList(pivot));
        mergeList.addAll(sort(rightList));

        return mergeList;
    }

    public void sort(int[] arr, int low, int high) {
        if (low >= high) { return; }

        final int pivot = arr[low];
        int left = low;
        int right = high;
        int temp;

        while (left < right) {
            while (pivot < arr[right]) { right--; }
            while (left < right && arr[left] <= pivot) { left++; }

            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        arr[low] = arr[left];
        arr[left] = pivot;

        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }
}
