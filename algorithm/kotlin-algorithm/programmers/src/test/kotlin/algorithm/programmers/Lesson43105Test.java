package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson43105Test {
    @Test
    void case_1() {
        var triangle = new int[][]{
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        var actual = new Lesson43105().solution(triangle);
        assertThat(actual).isEqualTo(30);
    }
}
