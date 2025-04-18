package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson12907Test {
    @Test
    void case_1() {
        var n = 5;
        var money = new int[]{1, 2, 5};

        var actual = new Lesson12907().solution(n, money);

        assertThat(actual).isEqualTo(4);
    }
}
