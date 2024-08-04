package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson250137Test {
    @Test
    void case_1() {
        var bandage = new int[]{5, 1, 5};
        var health = 30;
        var attacks = new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}};

        var actual = new Lesson250137().solution(bandage, health, attacks);

        assertThat(actual).isEqualTo(5);
    }

    @Test
    void case_2() {
        var bandage = new int[]{3, 2, 7};
        var health = 20;
        var attacks = new int[][]{{1, 15}, {5, 16}, {8, 6}};

        var actual = new Lesson250137().solution(bandage, health, attacks);

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    void case_3() {
        var bandage = new int[]{4, 2, 7};
        var health = 20;
        var attacks = new int[][]{{1, 15}, {5, 16}, {8, 6}};

        var actual = new Lesson250137().solution(bandage, health, attacks);

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    void case_4() {
        var bandage = new int[]{1, 1, 1};
        var heath = 5;
        var attacks = new int[][]{{1, 2}, {3, 2}};

        var actual = new Lesson250137().solution(bandage, heath, attacks);

        assertThat(actual).isEqualTo(3);
    }
}
