package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12973Test {
    @Test
    void case_1() {
        var s = "baabaa";
        var actual = new Lesson12973().solution(s);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void case_2() {
        var s = "cdcd";
        var actual = new Lesson12973().solution(s);
        assertThat(actual).isEqualTo(0);
    }
}
