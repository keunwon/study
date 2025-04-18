package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson1829Test {
    @Test
    void case_1() {
        var m = 6;
        var n = 4;
        var picture = new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        var actual = new Lesson1829().solution(m, n, picture);

        assertThat(actual).containsExactly(4, 5);
    }
}
