package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12936Test {
    @Test
    void case1() {
        var n = 3;
        var k = 5L;

        var actual = new Lesson12936().solution(n, k);

        assertThat(actual).containsExactly(3, 1, 2);
    }
}