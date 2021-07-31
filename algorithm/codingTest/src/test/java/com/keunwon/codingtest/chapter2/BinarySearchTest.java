package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BinarySearchTest {

    @Test
    @DisplayName("이진 탐색")
    public void binarySearch() {
        // given
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5, 6);
        BinarySearch binarySearch = new BinarySearch();

        // when, then
        assertThat(binarySearch.search(dataList, 1)).isTrue();
        assertThat(binarySearch.search(dataList, 2)).isTrue();
        assertThat(binarySearch.search(dataList, 3)).isTrue();
        assertThat(binarySearch.search(dataList, 4)).isTrue();
        assertThat(binarySearch.search(dataList, 5)).isTrue();
        assertThat(binarySearch.search(dataList, 6)).isTrue();
        assertThat(binarySearch.search(dataList, 10)).isFalse();
    }
}