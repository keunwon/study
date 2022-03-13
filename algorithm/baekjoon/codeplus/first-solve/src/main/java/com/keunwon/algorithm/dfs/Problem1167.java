package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1167 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static List<Node>[] graph;

    static int maxIdx;
    static int totalDistance;


    public static void main(String[] args) throws IOException {
        input();

        dfs(1, 0, new boolean[N + 1]);

        totalDistance = 0;
        dfs(maxIdx, 0, new boolean[N + 1]);

        System.out.println(totalDistance);
    }

    static void dfs(int x, int distance, boolean[] visited) {
        if (totalDistance < distance) {
            totalDistance = distance;
            maxIdx = x;
        }

        visited[x] = true;

        List<Node> childNodes = graph[x];
        for (Node childNode : childNodes) {
            if (!visited[childNode.idx]) {
                dfs(childNode.idx, childNode.distance + distance, visited);
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parentIdx = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int childIdx = Integer.parseInt(st.nextToken());

                if (childIdx == -1) { break; }
                int distance = Integer.parseInt(st.nextToken());

                graph[parentIdx].add(new Node(childIdx, distance));
                graph[childIdx].add(new Node(parentIdx, distance));
            }
        }
    }

    static class Node {
        int idx;
        int distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }
}
