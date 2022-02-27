package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem14442 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M, K;
    private static int[][] board;

    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Position>();

        queue.offer(new Position(0, 0, 0, 1));
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

                if (board[nx][ny] == 1 && p.broken + 1 <= K && !visited[p.broken + 1][nx][ny]) {
                    queue.offer(new Position(nx, ny, p.broken + 1, p.distance + 1));
                    visited[p.broken + 1][nx][ny] = true;
                } else if (board[nx][ny] == 0 && !visited[p.broken][nx][ny]) {
                    queue.offer(new Position(nx, ny, p.broken, p.distance + 1));
                    visited[p.broken][nx][ny] = true;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            var line = br.readLine();

            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
    }

    private static class Position {
        int x;
        int y;
        int broken;
        int distance;

        Position(int x, int y, int broken, int distance) {
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.distance = distance;
        }
    }
}
