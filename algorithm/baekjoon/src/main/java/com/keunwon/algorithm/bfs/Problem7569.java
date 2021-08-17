package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem7569 {

    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;

    static int[][] moves = { {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, 0}, {1, 0, 0} };

    public static void main(String[] args) throws IOException {
        input();

        bfs();
    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        queue.add(new Position(i, j, k));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nh = p.h + move[0];
                int nr = p.x + move[1];
                int nc = p.y + move[2];

                if (validMap(nh, nr, nc) && isGo(nh, nr, nc)) {
                    queue.add(new Position(nh, nr, nc));
                    visited[nh][nr][nc] = true;
                    map[nh][nr][nc] = map[p.h][p.x][p.y] + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 0) {
                        System.out.println("-1");
                        System.exit(0);
                    } else {
                        max = Math.max(max, map[i][j][k]);
                    }
                }
            }
        }

        System.out.println(--max);
    }

    static boolean isGo(int h, int r, int c) {
        return map[h][r][c] == 0 && !visited[h][r][c];
    }

    static boolean validMap(int h, int r, int c) {
        return h >= 0 && h < H && r >= 0 && r < N && c >= 0 && c < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static class Position {
        int h;
        int x;
        int y;

        public Position(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }
}
