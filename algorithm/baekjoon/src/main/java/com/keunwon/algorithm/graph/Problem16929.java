package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem16929 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) { continue; }

                Position pre = new Position(-1, -1);
                Position now = new Position(i, j);

                if (dfs(pre, now, map[i][j])) {
                    System.out.println("Yes");
                    System.exit(0);
                }
            }
        }
        System.out.println("No");
    }

    private static boolean dfs(Position pre, Position now, char color) {
        if (visited[now.x][now.y]) {
            return true;
        }

        visited[now.x][now.y] = true;

        for (int[] move : moves) {
            final int nx = now.x + move[0];
            final int ny = now.y + move[1];

            if (!validMap(nx, ny)) { continue; }
            if (color != map[nx][ny]) { continue; }
            if (pre.x == nx && pre.y == ny) { continue; }

            if (dfs(now, new Position(nx, ny), color)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }

    private static class Position {
        final int x;
        final int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
