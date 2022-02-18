package com.keunwon.algorithm.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem11725 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<List<Integer>> graph;

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        input();
        dfs(1);

        for (int i = 2; i <= N; i++) {
            bw.write(parents[i] + System.lineSeparator());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x) {
        for (Integer num : graph.get(x)) {
            if (parents[num] == 0) {
                parents[num] = x;
                dfs(num);
            }
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }
    }
}
