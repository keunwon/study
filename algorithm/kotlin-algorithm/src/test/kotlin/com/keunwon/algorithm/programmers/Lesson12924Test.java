package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12924Test {
    @Test
    void case_1() {
        var n = 15;
        var actual = new Lesson12924().solution(n);
        assertThat(actual).isEqualTo(4);
    }
}
