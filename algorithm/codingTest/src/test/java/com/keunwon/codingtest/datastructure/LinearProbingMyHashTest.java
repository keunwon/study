package com.keunwon.codingtest.datastructure;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LinearProbingMyHashTest {

    @Test
    public void case_01() {
        // given
        LinearProbingMyHash hash = new LinearProbingMyHash(20);
        hash.saveData("DaveLee", "01011112222");
        hash.saveData("fun-coding", "01033334444");
        hash.saveData("David", "01044445555");
        hash.saveData("Dave", "01066667777");

        // when, then
        assertThat(hash.getData("DaveLee")).isEqualTo("01011112222");
        assertThat(hash.getData("fun-coding")).isEqualTo("01033334444");
        assertThat(hash.getData("David")).isEqualTo("01044445555");
        assertThat(hash.getData("Dave")).isEqualTo("01066667777");
    }
}