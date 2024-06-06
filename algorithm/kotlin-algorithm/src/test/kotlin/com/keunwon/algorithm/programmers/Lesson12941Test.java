package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12941Test {
    @Test
    void case_1() {
        var a = new int[]{1, 4, 2};
        var b = new int[]{5, 4, 4};

        var actual = new Lesson12941().solution(a, b);

        assertThat(actual).isEqualTo(29);
    }

    @Test
    void case_2() {
        var a = new int[]{1, 2};
        var b = new int[]{3, 4};

        var actual = new Lesson12941().solution(a, b);

        assertThat(actual).isEqualTo(10);
    }
}
