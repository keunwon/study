package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1520 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int row;
    static int column;
    static int[][] map;

    static int[][] moves = { {-1, 0}, {0 , 1}, {1, 0}, {0, -1} };
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        if (r == row - 1 && c == column - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }


        dp[r][c] = 0;
        int preNum = map[r][c];

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) &&  map[nr][nc] < map[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        map = new int[row][column];
        dp = new int[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
    }
}
