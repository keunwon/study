package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SelectorSortTest {

    @Test
    @DisplayName("선택 정렬")
    public void sort() {
        // given
        List<Integer> dataList = Arrays.asList(10, 1, 3, 2, 5, 4, 7, 8, 9, 6);

        // when
        List<Integer> sortList = new SelectorSort().sort(dataList);

        // then
        assertThat(sortList).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}