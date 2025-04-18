package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson42576Test {
    @Test
    void case_1() {
        var participant = new String[]{"leo", "kiki", "eden"};
        var completion = new String[]{"eden", "kiki"};

        var actual = new Lesson42576().solution(participant, completion);

        assertThat(actual).isEqualTo("leo");
    }

    @Test
    void case_2() {
        var participant = new String[]{"marina", "josipa", "nikola", "vinko", "filipa"};
        var completion = new String[]{"josipa", "filipa", "marina", "nikola"};

        var actual = new Lesson42576().solution(participant, completion);

        assertThat(actual).isEqualTo("vinko");
    }

    @Test
    void case_3() {
        var participant = new String[]{"mislav", "stanko", "mislav", "ana"};
        var completion = new String[]{"stanko", "ana", "mislav"};

        var actual = new Lesson42576().solution(participant, completion);

        assertThat(actual).isEqualTo("mislav");
    }
}
