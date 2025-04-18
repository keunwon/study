package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17681Test {
    @Test
    void case_1() {
        var n = 5;
        var arr1 = new int[]{9, 20, 28, 18, 11};
        var arr2 = new int[]{30, 1, 21, 17, 28};

        var actual = new Lesson17681().solution(n, arr1, arr2);

        assertThat(actual).containsExactly("#####", "# # #", "### #", "#  ##", "#####");
    }

    @Test
    void case_2() {
        var n = 6;
        var arr1 = new int[]{46, 33, 33, 22, 31, 50};
        var arr2 = new int[]{27, 56, 19, 14, 14, 10};

        var actual = new Lesson17681().solution(n, arr1, arr2);

        assertThat(actual).containsExactly("######", "###  #", "##  ##", " #### ", " #####", "### # ");
    }
}
