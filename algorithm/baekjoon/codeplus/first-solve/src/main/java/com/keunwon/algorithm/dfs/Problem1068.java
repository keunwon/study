package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1068 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int rootIdx;
    static int removeIdx;
    static List<Integer>[] graph;

    static int leafNodeCount = 0;


    public static void main(String[] args) throws IOException {
        input();

        if (rootIdx == removeIdx) {
            System.out.println(0);
            return;
        }

        dfs(rootIdx, new boolean[N]);
        System.out.println(leafNodeCount);
    }

    static void dfs(int x, boolean[] visited) {
        visited[x] = true;

        int count = 0;
        List<Integer> childIdx = graph[x];
        for (Integer idx : childIdx) {
            if (!visited[idx] && removeIdx != idx) {
                dfs(idx, visited);
                count++;
            }
        }

        if (count == 0) {
            leafNodeCount++;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());

            if (idx == -1) {
                rootIdx = i;
                continue;
            }

            graph[i].add(idx);
            graph[idx].add(i);
        }
        
        removeIdx = Integer.parseInt(br.readLine());
    }
}
