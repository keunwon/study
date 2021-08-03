package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DijkstraPathTest {
    private static Map<String, List<Edge>> graph = new HashMap<>();


    @BeforeAll
    public static void init() {
        graph.put("A", Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D")));
        graph.put("B", emptyList());
        graph.put("C", Arrays.asList(new Edge(5, "B"), new Edge(2, "D")));
        graph.put("D", Arrays.asList(new Edge(3, "E"), new Edge(5, "F")));
        graph.put("E", Arrays.asList(new Edge(1, "F")));
        graph.put("F", Arrays.asList(new Edge(5, "A")));
    }

    @Test
    @DisplayName("최단경로알고리즘-다익스트라")
    public void DijkstraPath() {
        // given
        DijkstraPath dijkstraPath = new DijkstraPath();

        // when
        Map<String, Integer> resultMap = dijkstraPath.dijkstraFunc(graph, "A");

        // then
        assertThat(resultMap.get("A")).isEqualTo(0);
        assertThat(resultMap.get("B")).isEqualTo(6);
        assertThat(resultMap.get("C")).isEqualTo(1);
        assertThat(resultMap.get("D")).isEqualTo(2);
        assertThat(resultMap.get("E")).isEqualTo(5);
        assertThat(resultMap.get("F")).isEqualTo(6);
    }
}