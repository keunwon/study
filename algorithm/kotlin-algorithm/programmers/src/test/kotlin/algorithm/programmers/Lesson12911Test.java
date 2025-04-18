package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12911Test {
    @Test
    void case_1() {
        var n = 78;
        var actual = new Lesson12911().solution(n);
        assertThat(actual).isEqualTo(83);
    }

    @Test
    void case_2() {
        var n = 15;
        var actual = new Lesson12911().solution(n);
        assertThat(actual).isEqualTo(23);
    }
}
