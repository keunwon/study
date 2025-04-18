package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson1835Test {
    @Test
    void case_1() {
        var n = 2;
        var data = new String[]{"N~F=0", "R~T>2"};

        var actual = new Lesson1835().solution(n, data);

        assertThat(actual).isEqualTo(3648);
    }

    @Test
    void case_2() {
        var n = 2;
        var data = new String[]{"M~C<2", "C~M>1"};

        var actual = new Lesson1835().solution(n, data);

        assertThat(actual).isEqualTo(0);
    }
}
