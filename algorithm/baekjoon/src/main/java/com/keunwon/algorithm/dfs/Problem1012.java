package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1012 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int row;
    private static int column;
    private static int[][] map;
    private static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            input();
            count = 0;

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    if (isGo(j, k)) {
                        go(j, k);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void go(int r, int c) {
        checked[r][c] = true;

        int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc)) {
                go(nr, nc);
            }
        }
    }

    private static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    private static boolean isGo(int r, int c) {
        return map[r][c] == 1 && !checked[r][c];
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        checked = new boolean[row][column];
        map = new int[row][column];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            map[v1][v2] = 1;
        }
    }
}
