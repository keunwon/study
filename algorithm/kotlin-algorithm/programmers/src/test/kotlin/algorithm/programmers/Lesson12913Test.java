package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12913Test {
    @Test
    void case_1() {
        var land = new int[][]{
                {1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1},
        };
        var actual = new Lesson12913().solution(land);
        assertThat(actual).isEqualTo(16);
    }
}
