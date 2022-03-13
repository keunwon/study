package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1967 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static List<Node>[] graph;
    static boolean[] visited;
    static int maxNode = 0;
    static int maxTotalDistance = 0;

    public static void main(String[] args) throws IOException {
        input();

        visited = new boolean[N + 1];
        dfs(1, 0);

        maxTotalDistance = 0;
        visited = new boolean[N + 1];
        dfs(maxNode, 0);

        System.out.println(maxTotalDistance);
    }

    static void dfs(int x, int distance) {
        if (maxTotalDistance < distance) {
            maxTotalDistance = distance;
            maxNode = x;
        }

        visited[x] = true;

        List<Node> childNodes = graph[x];
        for (Node childNode : childNodes) {
            if (!visited[childNode.idx]) {
                dfs(childNode.idx, childNode.distance + distance);
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, distance));
            graph[n2].add(new Node(n1, distance));
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
