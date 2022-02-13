package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1707 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int K;
    private static int N, M;
    private static List<List<Integer>> graph;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            input();
            bfs(1);
        }
    }

    private static void bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
             if (visited[i] == 0) {
                 queue.add(i);
                 visited[i] = 1;
             }

             while (!queue.isEmpty()) {
                 final int num = queue.poll();

                 for (Integer value : graph.get(num)) {
                     if (visited[value] == 0) {
                         queue.add(value);
                     }

                     if (visited[value] == visited[num]) {
                         System.out.println("NO");
                         return;
                     }

                     if (visited[num] == 1 && visited[value] == 0) {
                         visited[value] = 2;
                     } else if (visited[num] == 2 && visited[value] == 0) {
                         visited[value] = 1;
                     }
                 }
             }
        }
        System.out.println("YES");
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visited = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }
    }
}
