package com.keunwon.algorithm.programmers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17683Test {
    @Test
    void case_1() {
        var m = "ABCDEFG";
        var musicinfos = new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        var actual = new Lesson17683().solution(m, musicinfos);

        assertThat(actual).isEqualTo("HELLO");
    }

    @Test
    void case_2() {
        var m = "CC#BCC#BCC#BCC#B";
        var musicinfos = new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        var actual = new Lesson17683().solution(m, musicinfos);

        assertThat(actual).isEqualTo("FOO");
    }

    @Test
    void case_3() {
        var m = "ABC";
        var musicinfos = new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        var actual = new Lesson17683().solution(m, musicinfos);

        assertThat(actual).isEqualTo("WORLD");
    }
}
