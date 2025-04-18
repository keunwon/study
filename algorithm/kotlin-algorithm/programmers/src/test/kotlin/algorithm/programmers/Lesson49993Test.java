package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson49993Test {
    @Test
    void case_1() {
        var skill = "CBD";
        var skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};

        var actual = new Lesson49993().solution(skill, skill_trees);

        assertThat(actual).isEqualTo(2);
    }
}
