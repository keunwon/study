package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42626Test {
    @Test
    void case_1() {
        var scoville = new int[]{1, 2, 3, 9, 10, 12};
        var k = 7;

        var actual = new Lesson42626().solution(scoville, k);

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void case_2() {
        var scoville = new int[]{1, 1, 1, 1};
        var k = 9;

        var actual = new Lesson42626().solution(scoville, k);

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_3() {
        var scoville = new int[]{9, 9, 10};
        var k = 9;

        var actual = new Lesson42626().solution(scoville, k);

        assertThat(actual).isEqualTo(0);
    }
}
