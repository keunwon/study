package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12927Test {
    @Test
    void case_1() {
        var works = new int[]{4, 3, 3};
        var n = 4;

        var actual = new Lesson12927().solution(n, works);

        assertThat(actual).isEqualTo(12);
    }

    @Test
    void case_2() {
        var works = new int[]{2, 1, 2};
        var n = 1;

        var actual = new Lesson12927().solution(n, works);

        assertThat(actual).isEqualTo(6);
    }

    @Test
    void case_3() {
        var works = new int[]{1, 1};
        var n = 3;

        var actual = new Lesson12927().solution(n, works);

        assertThat(actual).isEqualTo(0);
    }
}
