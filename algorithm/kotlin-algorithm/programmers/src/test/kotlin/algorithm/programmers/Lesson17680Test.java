package algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17680Test {
    @Test
    void case_1() {
        var cacheSize = 3;
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(50);
    }

    @Test
    void case_2() {
        var cacheSize = 3;
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(21);
    }

    @Test
    void case_3() {
        var cacheSize = 2;
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(60);
    }

    @Test
    void case_4() {
        var cacheSize = 5;
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(52);
    }

    @Test
    void case_5() {
        var cacheSize = 2;
        var cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(16);
    }

    @Test
    void case_6() {
        var cacheSize = 0;
        var cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        var actual = new Lesson17680().solution(cacheSize, cities);

        assertThat(actual).isEqualTo(25);
    }
}
