package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem15649 {
    private static int N;
    private static int M;
    private static int[] arr;
    private static boolean[] used;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        output();
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i]) { continue; }

            arr[depth] = i;
            used[i] = true;

            dfs(depth + 1);

            used[i] = false;
            arr[depth] = 0;
        }
    }

    private static void output() {
        System.out.println(sb.toString());
    }

    private static void input() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[M];
            used = new boolean[N + 1];
        }
    }
}
