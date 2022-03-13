package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2589 {
    static int N, M;
    static char[][] map;

    static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    result = Math.max(result, bfs(i, j));
                }
            }
        }
        System.out.println(result);
    }

    static int bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int hours = 0;

        queue.add(new Position(x, y, 0));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (!validMap(nx, ny) || visited[nx][ny]) { continue; }

                if (map[nx][ny] != 'L') { continue; }

                queue.add(new Position(nx, ny, p.hours + 1));
                visited[nx][ny] = true;
                hours = Math.max(hours, p.hours + 1);
            }
        }
        return hours;
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }

    static class Position {
        int x;
        int y;
        int hours;

        public Position(int x, int y, int hours) {
            this.x = x;
            this.y = y;
            this.hours = hours;
        }
    }
}
