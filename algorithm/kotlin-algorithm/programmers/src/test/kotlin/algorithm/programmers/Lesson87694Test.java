package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson87694Test {
    @Test
    void case_1() {
        var rectangle = new int[][]{
                {1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        var characterX = 1;
        var characterY = 3;
        var itemX = 7;
        var itemY = 8;

        var actual = new Lesson87694().solution(rectangle, characterX, characterY, itemX, itemY);

        assertThat(actual).isEqualTo(17);
    }

    @Test
    void case_2() {
        var rectangle = new int[][]{
                {1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}};
        var characterX = 9;
        var characterY = 7;
        var itemX = 6;
        var itemY = 1;

        var actual = new Lesson87694().solution(rectangle, characterX, characterY, itemX, itemY);

        assertThat(actual).isEqualTo(11);
    }

    @Test
    void case_3() {
        var rectangle = new int[][]{{1, 1, 5, 7}};
        var characterX = 1;
        var characterY = 1;
        var itemX = 4;
        var itemY = 7;

        var actual = new Lesson87694().solution(rectangle, characterX, characterY, itemX, itemY);

        assertThat(actual).isEqualTo(9);
    }

    @Test
    void case_4() {
        var rectangle = new int[][]{{2, 1, 7, 5}, {6, 4, 10, 10}};
        var characterX = 3;
        var characterY = 1;
        var itemX = 7;
        var itemY = 10;

        var actual = new Lesson87694().solution(rectangle, characterX, characterY, itemX, itemY);

        assertThat(actual).isEqualTo(15);
    }

    @Test
    void case_5() {
        var rectangle = new int[][]{{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}};
        var characterX = 1;
        var characterY = 4;
        var itemX = 6;
        var itemY = 3;

        var actual = new Lesson87694().solution(rectangle, characterX, characterY, itemX, itemY);

        assertThat(actual).isEqualTo(10);
    }
}
