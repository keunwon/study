package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1103 {
    static int N;
    static int M;
    static int[][] map;

    static boolean isInfinite = false;
    static int maxDepth = 1;
    static boolean[][] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0, 1);

        if (isInfinite) {
            System.out.println(-1);
        } else {
            System.out.println(maxDepth);
        }
    }

    static void dfs(int r, int c, int depth) {
        if (maxDepth < depth) {
            maxDepth = depth;
        }

        dp[r][c] = depth;

        for (int[] move : getMoves(map[r][c])) {
            int row = r + move[0];
            int column = c + move[1];

            if (validMap(row, column) && isGo(row, column)) {
                System.out.println("searching... " + row + ", " + column);

                if (visited[row][column]) {
                    isInfinite = true;
                    return;
                }

                if (dp[row][column] > depth) {
                    System.out.println("사용?");
                    continue;
                }

                visited[row][column] = true;
                dfs(row, column, depth + 1);
                visited[row][column] = false;
            }
        }
    }

    static int[][] getMoves(int n) {
        return new int[][]{ {-n, 0}, {0, n}, {n, 0}, {0, -n} };
    }

    static boolean isGo(int r, int c) {
        return map[r][c] != 24;
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }
}
