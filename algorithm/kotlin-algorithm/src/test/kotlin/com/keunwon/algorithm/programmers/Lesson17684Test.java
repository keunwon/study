package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17684Test {
    @Test
    void case_1() {
        var msg = "KAKAO";
        var actual = new Lesson17684().solution(msg);
        assertThat(actual).containsExactly(11, 1, 27, 15);
    }

    @Test
    void case_2() {
        var msg = "TOBEORNOTTOBEORTOBEORNOT";
        var actual = new Lesson17684().solution(msg);
        assertThat(actual).containsExactly(20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34);
    }

    @Test
    void case_3() {
        var msg = "ABABABABABABABAB";
        var actual = new Lesson17684().solution(msg);
        assertThat(actual).containsExactly(1, 2, 27, 29, 28, 31, 30);
    }
}
