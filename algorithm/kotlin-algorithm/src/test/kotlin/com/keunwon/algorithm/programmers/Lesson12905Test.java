package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12905Test {
    @Test
    void case_1() {
        var board = new int[][]{
                {0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}
        };
        var actual = new Lesson12905().solution(board);
        assertThat(actual).isEqualTo(9);
    }

    @Test
    void case_2() {
        var board = new int[][]{{0, 0, 1, 1}, {1, 1, 1, 1}};
        var actual = new Lesson12905().solution(board);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void case_3() {
        var board = new int[][]{{1, 0}, {0, 0}};
        var actual = new Lesson12905().solution(board);
        assertThat(actual).isEqualTo(1);
    }
}
