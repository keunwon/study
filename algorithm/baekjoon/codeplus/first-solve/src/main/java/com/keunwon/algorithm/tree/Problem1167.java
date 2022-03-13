package com.keunwon.algorithm.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1167 {
   private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

   private static int V;
   private static List<List<Node>> graph;

   private static boolean[] visited;
   private static int maxDistance = 0;
   private static int index = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(index, 0);

        System.out.println(maxDistance);
    }

    private static void dfs(int x, int distance) {
        if (maxDistance < distance) {
            maxDistance = distance;
            index = x;
        }

        visited[x] = true;

        for (Node node : graph.get(x)) {
            if (!visited[node.x]) {
                dfs(node.x, node.distance + distance);
            }
        }
    }

    private static void input() throws IOException {
        V = Integer.parseInt(br.readLine());
        visited = new boolean[V + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int nIndex = Integer.parseInt(st.nextToken());

                if (nIndex == -1) { break; }

                int distance = Integer.parseInt(st.nextToken());

                graph.get(index).add(new Node(nIndex, distance));
                graph.get(nIndex).add(new Node(index, distance));
            }
        }
    }


    private static class Node {
        final int x;
        final int distance;

        Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }
}
