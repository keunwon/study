package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12899Test {
    @Test
    void case_1() {
        var n = 1;
        var actual = new Lesson12899().solution(n);
        assertThat(actual).isEqualTo("1");
    }

    @Test
    void case_2() {
        var n = 2;
        var actual = new Lesson12899().solution(n);
        assertThat(actual).isEqualTo("2");
    }

    @Test
    void case_3() {
        var n = 3;
        var actual = new Lesson12899().solution(n);
        assertThat(actual).isEqualTo("4");
    }

    @Test
    void case_4() {
        var n = 4;
        var actual = new Lesson12899().solution(n);
        assertThat(actual).isEqualTo("11");
    }
}
