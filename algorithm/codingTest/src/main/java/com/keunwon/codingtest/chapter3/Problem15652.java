package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem15652 {
    private static int N;
    private static int M;
    private static int arr[];
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        dfs(1);
        output();
    }

    private static void dfs(int depth) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");

            System.out.println(sb.toString());
            return;
        }

        int start = arr[depth - 1];
        if (start == 0) { start += 1; }

        for (int i = start; i <= N; i++) {
            arr[depth] = i;
            dfs(depth + 1);
            arr[depth] = 0;
        }
    }

    private static void input() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[M + 1];
        }
    }

    private static void output() {
        System.out.print(sb.toString());
    }
}
