package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12900Test {
    @Test
    void case_1() {
        var n = 4;
        var actual = new Lesson12900().solution(n);
        assertThat(actual).isEqualTo(5);
    }
}
