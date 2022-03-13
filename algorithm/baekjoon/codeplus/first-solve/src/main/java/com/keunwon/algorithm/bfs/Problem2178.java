package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2178 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    final static int[][] moves = { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws IOException {
        input();

        bfs(0, 0);
        System.out.println(map[N -1][M - 1]);
    }

    static void bfs(int r, int c) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(r, c));

        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nr = p.r + move[0];
                int nc = p.c + move[1];

                if (validMap(nr, nc) && isGo(nr, nc)) {
                    queue.add(new Position(nr, nc));
                    map[nr][nc] = map[p.r][p.c] + 1;
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static boolean isGo(int r, int c) {
        return map[r][c] != 0 && !visited[r][c];
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - '0';
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
