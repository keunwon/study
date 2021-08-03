package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class NQueenTest {

    @Test
    public void test() {
        // given
        NQueen nQueen = new NQueen();
        List<Integer> candidate = new ArrayList<>();

        // when
        nQueen.dfcFunc(4, 0, candidate);
    }
}