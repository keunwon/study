package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2146 {
    static int N;
    static int[][] map;

    static final int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        int idx = 1;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isGo(i, j, visited)) {
                    groupsMap(i, j, idx, visited);
                    idx++;
                }
            }
        }

        int minBridge = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 1) {
                    minBridge = Math.min(minBridge, madeBridge(i, j));
                }
            }
        }

        System.out.println(minBridge - 1);
    }

    static int madeBridge(int x, int y) {
        final Queue<Position> queue = new LinkedList<>();
        final boolean[][] visited = new boolean[N][N];
        final int idx = map[x][y];
        int minDistance = Integer.MAX_VALUE;

        queue.add(new Position(x, y, 0));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            if (otherGroup(p.x, p.y, idx)) {
                minDistance = Math.min(minDistance, p.distance);
                continue;
            }

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (validMap(nx, ny) && !sameGroup(nx, ny, idx) && !visited[nx][ny]) {
                    queue.add(new Position(nx, ny, p.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return minDistance;
    }

    static boolean sameGroup(int x, int y, int idx) {
        return map[x][y] == idx;
    }

    static boolean otherGroup(int x, int y, int idx) {
        return map[x][y] != idx && map[x][y] != 0;
    }

    static void groupsMap(int x, int y, int idx, boolean[][] visited) {
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(x, y, 0));
        visited[x][y] = true;
        map[x][y] = idx;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && isGo(nx, ny, visited)) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = idx;
                }
            }
        }
    }

    static boolean isGo(int x, int y, boolean[][] visited) {
        return map[x][y] == 1 && !visited[x][y];
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Position {
        int x;
        int y;
        int distance;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
