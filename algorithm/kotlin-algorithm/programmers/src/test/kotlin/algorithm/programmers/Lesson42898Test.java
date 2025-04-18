package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42898Test {
    @Test
    void case_1() {
        var m = 4;
        var n = 3;
        var puddles = new int[][]{{2, 2}};

        var actual = new Lesson42898().solution(m, n, puddles);

        assertThat(actual).isEqualTo(4);
    }
}
