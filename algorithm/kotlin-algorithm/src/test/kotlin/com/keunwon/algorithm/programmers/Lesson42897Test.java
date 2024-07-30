package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42897Test {
    @Test
    void case_1() {
        var money = new int[]{1, 2, 3, 1};
        var actual = new Lesson42897().solution(money);
        assertThat(actual).isEqualTo(4);
    }
}
