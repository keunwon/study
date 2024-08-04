package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12982Test {
    @Test
    void case_1() {
        var d = new int[]{1, 3, 2, 5, 4};
        var budget = 9;

        var actual = new Lesson12982().solution(d, budget);

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_2() {
        var d = new int[]{2, 2, 3, 3};
        var budget = 10;

        var actual = new Lesson12982().solution(d, budget);

        assertThat(actual).isEqualTo(4);
    }
}
