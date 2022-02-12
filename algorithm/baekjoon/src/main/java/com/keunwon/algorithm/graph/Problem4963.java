package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4963 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private static int w, h;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];
            visited = new boolean[h][w];

            if (w == 0 && h == 0) { break; }

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (isGo(i, j)) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int[] move : moves) {
            int nx = x + move[0];
            int ny = y + move[1];

            if (validMap(nx, ny) && isGo(nx, ny)) {
                dfs(nx, ny);
            }
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    private static boolean isGo(int x, int y) {
        return map[x][y] == 1 && !visited[x][y];
    }
}
