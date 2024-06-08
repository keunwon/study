package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42884Test {
    @Test
    void case_1() {
        var routes = new int[][]{
                {-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}
        };
        var actual = new Lesson42884().solution(routes);
        assertThat(actual).isEqualTo(2);
    }
}
