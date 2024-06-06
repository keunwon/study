package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12909Test {
    @Test
    void case_1() {
        var s = "()()";
        var actual = new Lesson12909().solution(s);
        assertThat(actual).isTrue();
    }

    @Test
    void case_2() {
        var s = "(())()";
        var actual = new Lesson12909().solution(s);
        assertThat(actual).isTrue();
    }

    @Test
    void case_3() {
        var s = ")()(";
        var actual = new Lesson12909().solution(s);
        assertThat(actual).isFalse();
    }

    @Test
    void case_4() {
        var s = "(()(";
        var actual = new Lesson12909().solution(s);
        assertThat(actual).isFalse();
    }
}
