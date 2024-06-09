package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17687Test {
    @Test
    void case_1() {
        var n = 2;
        var t = 4;
        var m = 2;
        var p = 1;

        var actual = new Lesson17687().solution(n, t, m, p);

        assertThat(actual).isEqualTo("0111");
    }

    @Test
    void case_2() {
        var n = 16;
        var t = 16;
        var m = 2;
        var p = 1;

        var actual = new Lesson17687().solution(n, t, m, p);

        assertThat(actual).isEqualTo("02468ACE11111111");
    }

    @Test
    void case_3() {
        var n = 16;
        var t = 16;
        var m = 2;
        var p = 2;

        var actual = new Lesson17687().solution(n, t, m, p);

        assertThat(actual).isEqualTo("13579BDF01234567");
    }
}
