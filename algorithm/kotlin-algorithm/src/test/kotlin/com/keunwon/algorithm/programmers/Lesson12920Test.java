package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12920Test {
    @Test
    void case1() {
        var n = 6;
        var cores = new int[]{1, 2, 3};

        var actual = new Lesson12920().solution(n, cores);

        assertThat(actual).isEqualTo(2);
    }
}