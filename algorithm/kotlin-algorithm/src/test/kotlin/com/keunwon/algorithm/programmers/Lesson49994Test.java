package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson49994Test {
    @Test
    void case_1() {
        var dirs = "ULURRDLLU";
        var actual = new Lesson49994().solution(dirs);
        assertThat(actual).isEqualTo(7);
    }

    @Test
    void case_2() {
        var dirs = "LULLLLLLU";
        var actual = new Lesson49994().solution(dirs);
        assertThat(actual).isEqualTo(7);
    }

    @Test
    void case_3() {
        var dirs = "UDU";
        var actual = new Lesson49994().solution(dirs);
        assertThat(actual).isEqualTo(1);
    }
}
