package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12980Test {
    @Test
    void case_1() {
        var n = 5;
        var actual = new Lesson12980().solution(n);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void case_2() {
        var n = 6;
        var actual = new Lesson12980().solution(n);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void case_3() {
        var n = 5000;
        var actual = new Lesson12980().solution(n);
        assertThat(actual).isEqualTo(5);
    }
}
