package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42584Test {
    @Test
    void case_1() {
        var prices = new int[]{1, 2, 3, 2, 3};
        var actual = new Lesson42584().solution(prices);
        assertThat(actual).containsExactly(4, 3, 1, 1, 0);
    }
}
