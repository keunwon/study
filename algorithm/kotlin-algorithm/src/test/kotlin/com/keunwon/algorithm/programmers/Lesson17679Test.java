package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17679Test {
    @Test
    void case1() {
        var m = 4;
        var n = 5;
        var board = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};

        var actual = new Lesson17679().solution(m, n, board);

        assertThat(actual).isEqualTo(14);
    }

    @Test
    void case_2() {
        var m = 6;
        var n = 6;
        var board = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        var actual = new Lesson17679().solution(m, n, board);

        assertThat(actual).isEqualTo(15);
    }
}
