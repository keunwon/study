package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson1845Test {
    @Test
    void case_1() {
        var nums = new int[]{3, 1, 2, 3};
        var actual = new Lesson1845().solution(nums);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void case_2() {
        var nums = new int[]{3, 3, 3, 2, 2, 4};
        var actual = new Lesson1845().solution(nums);
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_3() {
        var nums = new int[]{3, 3, 3, 2, 2, 2};
        var actual = new Lesson1845().solution(nums);
        assertThat(actual).isEqualTo(2);
    }
}
