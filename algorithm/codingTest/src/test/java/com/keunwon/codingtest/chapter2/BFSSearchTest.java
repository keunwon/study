package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BFSSearchTest {
    private static final Map<String, List<String>> graph = new HashMap<>();

    @BeforeAll
    public static void init() {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "G", "H", "I"));
        graph.put("D", Arrays.asList("B", "E", "F"));
        graph.put("E", singletonList("D"));
        graph.put("F", singletonList("D"));
        graph.put("G", singletonList("C"));
        graph.put("H", singletonList("C"));
        graph.put("I", Arrays.asList("C", "J"));
        graph.put("J", singletonList("I"));
    }


    @Test
    @DisplayName("너비 우선 탐색-BFS")
    public void bfs() {
        // given
        BFSSearch bfsSearch = new BFSSearch();

        // when
        List<String> searchA = bfsSearch.bfsFunc(graph, "A");

        // then
        assertThat(searchA).isEqualTo(Arrays.asList("A", "B", "C", "D", "G", "H", "I", "E", "F", "J"));
    }
}