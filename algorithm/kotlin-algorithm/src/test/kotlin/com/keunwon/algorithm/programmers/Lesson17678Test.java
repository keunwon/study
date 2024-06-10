package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17678Test {
    @Test
    void case_1() {
        var n = 1;
        var t = 1;
        var m = 5;
        var timetable = new String[]{"08:00", "08:01", "08:02", "08:03"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("09:00");
    }

    @Test
    void case_2() {
        var n = 2;
        var t = 10;
        var m = 2;
        var timetable = new String[]{"09:10", "09:09", "08:00"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("09:09");
    }

    @Test
    void case_3() {
        var n = 2;
        var t = 1;
        var m = 2;
        var timetable = new String[]{"09:00", "09:00", "09:00", "09:00"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("08:59");
    }

    @Test
    void case_4() {
        var n = 1;
        var t = 1;
        var m = 5;
        var timetable = new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("00:00");
    }

    @Test
    void case_5() {
        var n = 1;
        var t = 1;
        var m = 1;
        var timetable = new String[]{"23:59"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("09:00");
    }

    @Test
    void case_6() {
        var n = 10;
        var t = 60;
        var m = 45;
        var timetable = new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};

        var actual = new Lesson17678().solution(n, t, m, timetable);

        assertThat(actual).isEqualTo("18:00");
    }
}
