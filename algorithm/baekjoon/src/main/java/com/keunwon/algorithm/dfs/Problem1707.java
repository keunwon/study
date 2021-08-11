package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1707 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int row;
    static int column;
    static int[][] map;

    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static int[][] melt;

    public static void main(String[] args) throws IOException {
        input();

        int result = 0;
        int num = 0;

        while ((num = glacierNum()) < 2) {
            if (num == 0) {
                result = 0;
                break;
            }

            melt();
            result++;
        }

        System.out.println(result);
    }

    static int glacierNum() {
        boolean[][] visited = new boolean[row][column];
        
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc, visited)) {
                dfs(nr, nc, visited);
            }
        }
    }

    static void melt() {
        Queue<int[]> queue = new LinkedList<>();

        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] != 0) {
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int see = 0;

            for (int[] move : moves) {
                int nr = position[0] + move[0];
                int nc = position[1] + move[1];

                if (validMap(nr, nc) && isSee(nr, nc, visited)) {
                    see++;
                }
            }

            if (map[position[0]][position[1]] - see < 0) {
                map[position[0]][position[1]] = 0;
            } else {
                map[position[0]][position[1]] -= see;
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    static boolean isGo(int r, int c, boolean[][] visited) {
        return map[r][c] != 0 && !visited[r][c];
    }

    static boolean isSee(int r, int c, boolean[][] visited) {
        return !visited[r][c] && map[r][c] == 0;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        map = new int[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
