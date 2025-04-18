package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42577Test {
    @Test
    void case_1() {
        var phoneBook = new String[]{"119", "97674223", "1195524421"};
        var actual = new Lesson42577().solution(phoneBook);
        assertThat(actual).isFalse();
    }

    @Test
    void case_2() {
        var phoneBook = new String[]{"123", "456", "789"};
        var actual = new Lesson42577().solution(phoneBook);
        assertThat(actual).isTrue();
    }

    @Test
    void case_3() {
        var phoneBook = new String[]{"12", "123", "1235", "567", "88"};
        var actual = new Lesson42577().solution(phoneBook);
        assertThat(actual).isFalse();
    }
}
