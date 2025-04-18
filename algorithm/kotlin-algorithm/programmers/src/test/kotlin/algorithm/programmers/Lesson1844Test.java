package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson1844Test {
    @Test
    void case_1() {
        var maps = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},
        };
        var actual = new Lesson1844().solution(maps);
        assertThat(actual).isEqualTo(11);
    }

    @Test
    void case_2() {
        var maps = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };
        var actual = new Lesson1844().solution(maps);
        assertThat(actual).isEqualTo(-1);
    }
}
