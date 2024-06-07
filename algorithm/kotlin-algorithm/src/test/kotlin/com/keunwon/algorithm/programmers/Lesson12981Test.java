package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12981Test {
    @Test
    void case_1() {
        var n = 3;
        var words = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        var actual = new Lesson12981().solution(n, words);
        assertThat(actual).containsExactly(3, 3);
    }

    @Test
    void case_2() {
        var n = 5;
        var words = new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        var actual = new Lesson12981().solution(n, words);
        assertThat(actual).containsExactly(0, 0);
    }

    @Test
    void case_3() {
        var n = 2;
        var words = new String[]{"hello", "one", "even", "never", "now", "world", "draw"};
        var actual = new Lesson12981().solution(n, words);
        assertThat(actual).containsExactly(1, 3);
    }
}
