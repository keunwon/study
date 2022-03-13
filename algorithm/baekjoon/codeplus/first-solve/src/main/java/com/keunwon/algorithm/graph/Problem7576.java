package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem7576 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static Queue<Position> queue = new LinkedList<>();

    private static int M, N;
    private static int[][] map;
    private static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        input();
        bfs();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                } else {
                    max = Math.max(max, map[i][j]);
                }
            }
        }

        System.out.println(max - 1);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            final Position p = queue.poll();

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (validMap(nx, ny) && isGo(nx, ny)) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = map[p.x][p.y] + 1;
                }
            }
        }
    }

    private static boolean isGo(int x, int y) {
        return map[x][y] == 0 && !visited[x][y];
    }

    private static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                final int num = Integer.parseInt(st.nextToken());

                if (num == 1) {
                    queue.add(new Position(i, j));
                    visited[i][j] = true;
                }
                map[i][j] = num;
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
