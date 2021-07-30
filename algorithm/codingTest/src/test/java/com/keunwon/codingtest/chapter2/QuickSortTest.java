package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class QuickSortTest {

    @Test
    @DisplayName("퀵 정렬-리스트")
    public void sortList() {
        // given
        List<Integer> list = Arrays.asList(20, 10, 70, 80, 40, 90);

        // when
        List<Integer> sortedList = new QuickSort().sort(list);

        // then
        assertThat(sortedList).isEqualTo(Arrays.asList(10, 20, 40, 70, 80, 90));
    }

    @Test
    @DisplayName("퀵 정렬-배열")
    public void sorArray() {
        // given
        int[] arr = {20, 10, 70, 80, 40, 90};

        // when
        new QuickSort().sort(arr, 0, arr.length - 1);

        // then
        assertThat(arr).isEqualTo(new int[] {10, 20, 40, 70, 80, 90});
    }
}