package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12971Test {
    @Test
    void case_1() {
        var sticker = new int[]{14, 6, 5, 11, 3, 9, 2, 10};
        var actual = new Lesson12971().solution(sticker);
        assertThat(actual).isEqualTo(36);
    }

    @Test
    void case_2() {
        var sticker = new int[]{1, 3, 2, 5, 4};
        var actual = new Lesson12971().solution(sticker);
        assertThat(actual).isEqualTo(8);
    }
}
