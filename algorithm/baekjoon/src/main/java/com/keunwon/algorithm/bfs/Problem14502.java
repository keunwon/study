package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem14502 {

    static int N, M;
    static int[][] map;

    final static List<Position> virusPositions = new ArrayList<>();
    final static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static int max;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0);

        System.out.println(max);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            int[][] tempMap = new int[N][M];

            arrayCopy(map, tempMap);
            bfs();
            maxSafeZone();
            arrayCopy(tempMap, map);

            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>(virusPositions);

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && map[nx][ny] == 0) {
                    queue.add(new Position(nx, ny));
                    map[nx][ny] = 2;
                }
            }
        }
    }

    static void maxSafeZone() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }

        max = Math.max(max, count);
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void arrayCopy(int[][] copy, int[][] paste) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                paste[i][j] = copy[i][j];
            }
        }
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

                if (map[i][j] == 2) {
                    virusPositions.add(new Position(i, j));
                }
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
