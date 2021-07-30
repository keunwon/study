package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MergeSortTest {

    @Test
    @DisplayName("병합정렬")
    public void sortList() {
        List<Integer> testData = new ArrayList<>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        MergeSort mergeSort = new MergeSort();
        System.out.println(mergeSort.mergeSplitFunc(testData));
    }

    @Test
    @DisplayName("병합정렬-일반 배열")
    public void sortArr() {
        // given
        int[] arr = {20, 10, 70, 80, 40, 90};

        // when
        new MergeSort2().mergeSort(arr, 0, arr.length - 1);

        // then
        assertThat(arr).isEqualTo(new int[] {10, 20, 40, 70, 80, 90});
    }
}
