package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1261 {
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        System.out.println(0);
    }

    private static void bfs() {
        Queue<Position> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new Position(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                System.out.println(p.count);
                System.exit(0);
            }

            for (int[] move : moves) {
                final int nx = p.x + move[0];
                final int ny = p.y + move[1];

                if (!validMap(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                final int count = map[nx][ny] == 1 ? p.count + 1 : p.count;
                queue.offer(new Position(nx, ny, count));
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static class Position implements Comparable<Position> {
        int x;
        int y;
        int count;

        Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Position o) {
            return this.count - o.count;
        }
    }
}
