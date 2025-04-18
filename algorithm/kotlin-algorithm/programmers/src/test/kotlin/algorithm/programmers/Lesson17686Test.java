package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17686Test {
    @Test
    void case_1() {
        var files = new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        var actual = new Lesson17686().solution(files);
        assertThat(actual).containsExactly("img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png");
    }

    @Test
    void case_2() {
        var files = new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        var actual = new Lesson17686().solution(files);
        assertThat(actual).containsExactly("A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat");
    }
}
