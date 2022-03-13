package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2638 {
    static int N, M;
    static int[][] map;

    static int[][] visited;
    static List<Position> markingPositions = new ArrayList<>();
    static final int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        int count = 0;
        while (hasCheeses()) {
            visited = new int[N][M];

            bfs();
            count++;
        }

        System.out.println(count);
    }

    static boolean hasCheeses() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(0, 0));
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (!validMap(nx, ny)) { continue; }

                if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = 1;
                }

                if (map[nx][ny] == 1) {
                    if (++visited[nx][ny] >= 2) {
                        map[nx][ny] = 0;
                    }
                }
            }
        }
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
