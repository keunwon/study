package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12938Test {
    @Test
    void case_1() {
        var n = 2;
        var s = 9;

        var actual = new Lesson12938().solution(n, s);

        assertThat(actual).containsExactly(4, 5);
    }

    @Test
    void case_2() {
        var n = 2;
        var s = 1;

        var actual = new Lesson12938().solution(n, s);

        assertThat(actual).containsExactly(-1);
    }

    @Test
    void case_3() {
        var n = 2;
        var s = 8;

        var actual = new Lesson12938().solution(n, s);

        assertThat(actual).containsExactly(4, 4);
    }
}
