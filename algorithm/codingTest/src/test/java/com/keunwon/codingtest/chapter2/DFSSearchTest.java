package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DFSSearchTest {
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
    @DisplayName("깊이우선탐색-DFS")
    public void bfs() {
        // given
        DFSSearch dfsSearch = new DFSSearch();

        // then
        List<String> searchA = dfsSearch.dfsFunc(graph, "A");

        // then
        assertThat(searchA).isEqualTo(Arrays.asList("A", "C", "I", "J", "H", "G", "B", "D", "F", "E"));
    }
}