package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12987Test {
    @Test
    void case_1() {
        var a = new int[]{5, 1, 3, 7};
        var b = new int[]{2, 2, 6, 8};

        var actual = new Lesson12987().solution(a, b);

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_2() {
        var a = new int[]{2, 2, 2, 2};
        var b = new int[]{1, 1, 1, 1};

        var actual = new Lesson12987().solution(a, b);

        assertThat(actual).isEqualTo(0);
    }
}
