package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem2667 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final List<Integer> result = new ArrayList<>();

    private static int N;
    private static int[][] map;
    private static boolean[][] visited;

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isGo(i, j)) {
                    count = 1;
                    dfs(i, j);
                    result.add(count);
                }
            }
        }

        Collections.sort(result);

        System.out.println(result.size());
        result.forEach(System.out::println);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int[] move : moves) {
            final int nx = x + move[0];
            final int ny = y + move[1];

            if (validMap(nx, ny) && isGo(nx, ny)) {
                dfs(nx, ny);
                count++;
            }
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean isGo(int x, int y) {
        return map[x][y] == 1 && !visited[x][y];
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }
}
