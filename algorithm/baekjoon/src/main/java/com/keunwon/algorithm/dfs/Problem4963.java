package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4963 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int count;
    private static int row;
    private static int column;
    private static int[][] map;
    private static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        while (input()) {
            count = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (isGo(i, j)) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void dfs(int r, int c) {
        if (checked[r][c]) {
            return;
        }

        checked[r][c] = true;

        int[][] moves = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc)) {
                dfs(nr, nc);
            }
        }
    }

    private static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    private static boolean isGo(int r, int c) {
        return map[r][c] == 1 && !checked[r][c];
    }

    private static boolean input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        column = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row][column];
        checked = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return row != 0 && column != 0;
    }
}
