package com.keunwon.codingtest.chapter3;

import java.io.*;
import java.util.StringTokenizer;

public class Problem15651 {
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        output();
    }

    public static void dfs(int depth) throws IOException {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[depth] = i;
            dfs(depth + 1);
        }
    }

    private static void output() throws IOException {
        bw.flush();
        bw.close();
    }

    private static void input() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[M];
        }
    }
}
