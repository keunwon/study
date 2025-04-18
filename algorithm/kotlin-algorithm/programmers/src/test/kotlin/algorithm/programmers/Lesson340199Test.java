package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson340199Test {
    @Test
    void case1() {
        var wallet = new int[]{30, 15};
        var bill = new int[]{26, 17};

        var actual = new Lesson340199().solution(wallet, bill);

        assertThat(actual).isEqualTo(1);
    }

    @Test
    void case2() {
        var wallet = new int[]{50, 50};
        var bill = new int[]{100, 241};

        var actual = new Lesson340199().solution(wallet, bill);

        assertThat(actual).isEqualTo(4);
    }
}
