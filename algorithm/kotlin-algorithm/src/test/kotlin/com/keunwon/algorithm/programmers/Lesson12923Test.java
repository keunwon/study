package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12923Test {
    @Test
    void case_1() {
        var begin = 1L;
        var end = 10L;

        var actual = new Lesson12923().solution(begin, end);

        assertThat(actual).containsExactly(0, 1, 1, 2, 1, 3, 1, 4, 3, 5);
    }

    @Test
    void case_2() {
        var begin = 100000014L;
        var end = 100000016L;

        var actual = new Lesson12923().solution(begin, end);

        assertThat(actual).containsExactly(6, 5, 6250001);
    }
}
