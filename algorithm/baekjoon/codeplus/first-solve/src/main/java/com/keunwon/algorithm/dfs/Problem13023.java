package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem13023 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static List<Integer>[] graph;
    static boolean[] visited;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, 0);
        }
        System.out.println(0);
    }

    static void dfs(int x, int depth) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        visited[x] = true;
        for (Integer value : graph[x]) {
            if (!visited[value]) {
                dfs(value, depth + 1);
            }
        }
        visited[x] = false;
    }


    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N  = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            graph[t1].add(t2);
            graph[t2].add(t1);
        }
    }
}
