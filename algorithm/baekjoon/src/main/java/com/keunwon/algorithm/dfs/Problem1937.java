package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1937 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[][] map;

    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        dp[r][c] = 1;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc, map[r][c])) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
        return dp[r][c];
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static boolean isGo(int r, int c, int preNum) {
        return preNum < map[r][c];
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
