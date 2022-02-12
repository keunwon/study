package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem13023 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static boolean[] visited;
    private static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            dfs(i, 0);
        }

        System.out.println(0);
    }

    private static void dfs(int num, int depth) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        visited[num] = true;
        for (Integer value : graph[num]) {
            if (!visited[value]) {
                dfs(value, depth + 1);
            }
        }
        visited[num] = false;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            graph[num1].add(num2);
            graph[num2].add(num1);
        }
    }
}
