package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem16933 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M, K;
    private static int[][] map;

    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Position>();

        queue.offer(new Position(0, 0, Position.Time.DAY, 0, 1));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            var p = queue.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                System.out.println(p.distance);
                return;
            }

            for (int[] move : moves) {
                var nx = p.x + move[0];
                var ny = p.y + move[1];

                if (!validMap(nx, ny)) { continue; }

                if (isBroken(nx, ny, p) && Position.Time.DAY == p.time) {
                    queue.offer(new Position(nx, ny, Position.Time.NIGHT, p.broken + 1, p.distance + 1));
                    visited[p.broken + 1][nx][ny] = true;
                } else if (isBroken(nx, ny, p) && Position.Time.NIGHT == p.time) {
                    queue.offer(new Position(p.x, p.y, Position.Time.DAY, p.broken, p.distance + 1));
                } else if (map[nx][ny] == 0 && !visited[p.broken][nx][ny]) {
                    Position.Time time = Position.Time.DAY == p.time ? Position.Time.NIGHT : Position.Time.DAY;

                    queue.offer(new Position(nx, ny, time, p.broken, p.distance + 1));
                    visited[p.broken][nx][ny] = true;
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean isBroken(int x, int y, Position p) {
        return map[x][y] == 1
                && p.broken + 1 <= K
                && !visited[p.broken  +1][x][y];
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            var line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
    }

    private static class Position {
        int x;
        int y;
        Time time;
        int broken;
        int distance;

        Position(int x, int y, Time time, int broken, int distance) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.broken = broken;
            this.distance = distance;
        }

        private enum Time {
            DAY, NIGHT;
        }
    }
}
