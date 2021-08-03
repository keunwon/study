package com.keunwon.codingtest.chapter2;

import java.util.*;

public class DijkstraPath {

    public Map<String, Integer> dijkstraFunc(Map<String, List<Edge>> graph, String start) {
        Map<String, Integer> distancesMap = new HashMap<>();
        for (String key : graph.keySet()) {
            distancesMap.put(key, Integer.MAX_VALUE);
        }
        distancesMap.put(start, 0);

        Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(0, start));

        while (queue.size() > 0) {
            Edge node = queue.poll();

            if (distancesMap.get(node.getVertex()) < node.getDistance()) {
                continue;
            }

            List<Edge> adjacentNodes = graph.get(node.getVertex());
            for (Edge adjacentNode : adjacentNodes) {
                int totalDistance = node.getDistance() + adjacentNode.getDistance();

                if (totalDistance < distancesMap.get(adjacentNode.getVertex())) {
                    distancesMap.put(adjacentNode.getVertex(), totalDistance);
                    queue.add(new Edge(totalDistance, adjacentNode.getVertex()));
                }
            }
        }

        return distancesMap;
    }
}
