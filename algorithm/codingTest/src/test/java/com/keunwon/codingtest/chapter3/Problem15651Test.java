package com.keunwon.codingtest.chapter3;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem15651Test {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void case1() throws IOException {
        final String input = "3 1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problem15651.main(null);
    }

    @Test
    public void case2() throws IOException {
        final String input = "4 2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problem15651.main(null);
    }

    @Test
    public void case3() throws IOException {
        final String input = "3 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Problem15651.main(null);
    }
}