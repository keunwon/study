package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17685Test {
    @Test
    void case_1() {
        var words = new String[]{"go", "gone", "guild"};
        var actual = new Lesson17685().solution(words);
        assertThat(actual).isEqualTo(7);
    }

    @Test
    void case_2() {
        var words = new String[]{"abc", "def", "ghi", "jklm"};
        var actual = new Lesson17685().solution(words);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void case_3() {
        var words = new String[]{"word", "war", "warrior", "world"};
        var actual = new Lesson17685().solution(words);
        assertThat(actual).isEqualTo(15);
    }
}
