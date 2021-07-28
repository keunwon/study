package com.keunwon.codingtest.datastructure;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MyHashTest {

    @Test
    public void case_01() {
        // given
        MyHash myHash = new MyHash(20);
        myHash.saveData("DaveLee", "01011112222");
        myHash.saveData("fun-coding", "01033334444");

        // when, then
        assertThat(myHash.getData("DaveLee")).isEqualTo("01011112222");
        assertThat(myHash.getData("fun-coding")).isEqualTo("01033334444");
    }
}