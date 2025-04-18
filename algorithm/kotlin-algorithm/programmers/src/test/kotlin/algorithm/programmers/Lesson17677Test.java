package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17677Test {
    @Test
    void case_1() {
        var str1 = "FRANCE";
        var str2 = "french";

        var actual = new Lesson17677().solution(str1, str2);

        assertThat(actual).isEqualTo(16384);
    }

    @Test
    void case_2() {
        var str1 = "handshake";
        var str2 = "shake hands";

        var actual = new Lesson17677().solution(str1, str2);

        assertThat(actual).isEqualTo(65536);
    }

    @Test
    void case_3() {
        var str1 = "aa1+aa2";
        var str2 = "AAAA12";

        var actual = new Lesson17677().solution(str1, str2);

        assertThat(actual).isEqualTo(43690);
    }

    @Test
    void case_4() {
        var str1 = "E=M*C^2";
        var str2 = "e=m*c^2";

        var actual = new Lesson17677().solution(str1, str2);

        assertThat(actual).isEqualTo(65536);
    }
}
