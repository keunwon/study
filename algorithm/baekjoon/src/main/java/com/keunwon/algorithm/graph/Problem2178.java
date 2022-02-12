package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2178 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        input();
        bfs(0, 0);
        System.out.println(map[N - 1][M - 1]);
    }

    private static void bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            final Position p = queue.poll();

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (validMap(nx, ny) && isGo(nx, ny)) {
                    queue.add(new Position(nx, ny));
                    map[nx][ny] = map[p.x][p.y] + 1;
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isGo(int x, int y) {
        return map[x][y] == 1 && !visited[x][y];
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
