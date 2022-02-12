package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1260 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M, V;
    private static boolean[][] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        dfs(V);
        reset();
        bfs(V);
    }

    private static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        visited[num] = true;

        while (!queue.isEmpty()) {
            final int value = queue.poll();
            System.out.print(value + " ");

            for (int i = 1; i <= N; i++) {
                if (graph[value][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    private static void dfs(int num) {
        visited[num] = true;
        System.out.print(num + " ");

        for (int i = 1; i <= N; i++) {
            if (graph[num][i] && !visited[i]) {
                dfs(i);
            }
        }
    }

    private static void reset() {
        System.out.println();
        Arrays.fill(visited, false);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            graph[num1][num2] = graph[num2][num1] = true;
        }
    }
}
