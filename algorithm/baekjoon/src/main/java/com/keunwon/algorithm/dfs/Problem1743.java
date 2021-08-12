package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1743 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int row;
    static int column;
    static int[][] map;

    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static int tempDepth = 0;
    static int maxDepth = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        boolean[][] visited = new boolean[row + 1][column + 1];
        for (int i = 1; i <= row ; i++) {
            for (int j = 1; j <= column ; j++) {
                if (isGo(i, j, visited)) {
                    tempDepth = 1;
                    dfs(i, j, visited);
                    maxDepth = Math.max(maxDepth, tempDepth);
                }
            }
        }

        System.out.println(maxDepth);
    }

    static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc, visited)) {
                tempDepth++;
                dfs(nr, nc, visited);
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 1 && r <= row && c >=1 && c <= column;
    }

    static boolean isGo(int r, int c, boolean[][] visited) {
        return !visited[r][c] && map[r][c] == 1;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        map = new int[row + 1][column + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }
    }
}
