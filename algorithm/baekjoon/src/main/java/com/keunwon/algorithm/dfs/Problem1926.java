package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1926 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int row;
    static int column;
    static int[][] map;

    static int totalDepth = 0;
    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static List<Integer> result = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        input();

        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (isGo(i, j, visited)) {
                    totalDepth = 1;
                    dfs(i, j, visited);
                    result.add(totalDepth);
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println(0);
            System.out.println(0);
            return;
        }

        Collections.sort(result);
        System.out.println(result.size());
        System.out.println(result.get(result.size() - 1));
    }

    static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc, visited)) {
                totalDepth++;
                dfs(nr, nc, visited);
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >=0 && c < column;
    }

    static boolean isGo(int r, int c, boolean[][] visited) {
        return !visited[r][c] && map[r][c] == 1;
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
