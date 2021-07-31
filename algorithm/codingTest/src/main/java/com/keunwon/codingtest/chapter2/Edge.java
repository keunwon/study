package com.keunwon.codingtest.chapter2;

public class Edge implements Comparable<Edge> {
    private Integer distance;
    private String vertex;

    public Edge(Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Edge o) {
        return distance - o.getDistance();
    }

    public Integer getDistance() {
        return distance;
    }

    public String getVertex() {
        return vertex;
    }
}
