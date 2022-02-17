package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2146 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N;
    private static int[][] map;
    private static boolean[][] visited;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        int landName = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    groupingLand(i, j, landName);
                    landName++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    visited = new boolean[N][N];
                    madeBridge(i, j);
                }
            }
        }

        System.out.println(min);
    }

    private static void madeBridge(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        final int landName = map[x][y];

        visited[x][y] = true;
        queue.offer(new Position(x, y, 0));

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (validMap(nx, ny) && hasOtherLand(nx, ny, landName)) {
                    visited[nx][ny] = true;

                    if (map[nx][ny] == 0) {
                        queue.offer(new Position(nx, ny, p.distance + 1));
                        continue;
                    }
                    min = Math.min(min, p.distance);
                }
            }
        }
    }

    private static void groupingLand(int x, int y, int landName) {
        Queue<Position> queue = new LinkedList<>();

        visited[x][y] = true;
        map[x][y] = landName;
        queue.offer(new Position(x, y, 0));

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (validMap(nx, ny) && hasLandWithoutName(nx, ny)) {
                    visited[nx][ny] = true;
                    map[nx][ny] = landName;
                    queue.offer(new Position(nx, ny, 0));
                }
            }
        }
    }

    private static boolean hasOtherLand(int x, int y, int landName) {
        return map[x][y] != landName && !visited[x][y];
    }

    private static boolean hasLandWithoutName(int x, int y) {
        return map[x][y] == 1 && !visited[x][y];
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static class Position {
        final int x;
        final int y;
        int distance;

        Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
