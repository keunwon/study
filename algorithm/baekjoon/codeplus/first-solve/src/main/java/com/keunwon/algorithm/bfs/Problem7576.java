package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem7576 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();

        bfs();
    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    queue.add(new Position(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nr = p.r + move[0];
                int nc = p.c + move[1];

                if (validMap(nr, nc) && isGo(nr, nc)) {
                    queue.add(new Position(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = map[p.r][p.c] + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println("-1");
                    System.exit(0);
                } else {
                    max = Math.max(max, map[i][j]);
                }
            }
        }

        System.out.println(--max);
    }

    static boolean isGo(int r, int c) {
        return map[r][c] == 0 && !visited[r][c];
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >=0 && c < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Position {
        int r;
        int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
