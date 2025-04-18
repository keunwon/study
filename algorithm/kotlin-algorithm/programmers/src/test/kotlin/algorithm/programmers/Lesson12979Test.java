package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12979Test {
    @Test
    void case_1() {
        var n = 11;
        var stations = new int[]{4, 11};
        var w = 1;

        var actual = new Lesson12979().solution(n, stations, w);

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_2() {
        var n = 16;
        var stations = new int[]{9};
        var w = 2;

        var actual = new Lesson12979().solution(n, stations, w);

        assertThat(actual).isEqualTo(3);
    }
}
