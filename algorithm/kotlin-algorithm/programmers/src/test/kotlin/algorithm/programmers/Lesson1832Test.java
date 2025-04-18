package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson1832Test {
    @Test
    void case_1() {
        var m = 3;
        var n = 3;
        var cityMap = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        var actual = new Lesson1832().solution(m, n, cityMap);

        assertThat(actual).isEqualTo(6);
    }

    @Test
    void case_2() {
        var m = 3;
        var n = 6;
        var cityMap = new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        var actual = new Lesson1832().solution(m, n, cityMap);

        assertThat(actual).isEqualTo(2);
    }
}
