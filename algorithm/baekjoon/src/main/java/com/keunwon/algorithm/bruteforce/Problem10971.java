package com.keunwon.algorithm.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class Problem10971 {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N;
    public static int[][] distanceMap;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
            visited[i] = false;
        }

        System.out.println(min);
    }

    public static void dfs(int start, int now, int sum, int depth) {
        if (N - 1 == depth) {
            if (distanceMap[now][start] != 0) {
                sum += distanceMap[now][start];
                min = Math.min(min, sum);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] || distanceMap[now][i] == 0) {
                continue;
            }

            visited[i] = true;
            dfs(start, i, sum + distanceMap[now][i], depth + 1);
            visited[i] = false;
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        distanceMap = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                distanceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
