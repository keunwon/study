package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42885Test {
    @Test
    void case_1() {
        var people = new int[]{70, 50, 80, 50};
        var limit = 100;

        var actual = new Lesson42885().solution(people, limit);

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void case_2() {
        var people = new int[]{70, 80, 50};
        var limit = 100;

        var actual = new Lesson42885().solution(people, limit);

        assertThat(actual).isEqualTo(3);
    }
}
