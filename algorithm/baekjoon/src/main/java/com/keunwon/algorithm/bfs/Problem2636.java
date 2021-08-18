package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2636 {
    static int N, M;
    static int[][] map;

    static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        int hours = 0;
        int leftCake = leftCakeCount();

        while (true) {
            boolean[][] viisted = new boolean[N][M];

            if (melt(0, 0, viisted) > 0) {
                hours++;

                int count = leftCakeCount();
                if (count != 0) {
                    leftCake = count;
                }
                continue;
            }
            break;
        }

        System.out.println(hours);
        System.out.println(leftCake);;
    }

    static int melt(int x, int y, boolean[][] visited) {
        Queue<Position> queue = new LinkedList<>();
        int count = 0;

        queue.add(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            if (map[p.x][p.y] == 1) {
                map[p.x][p.y] = 0;
                count++;
                continue;
            }

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny, visited)) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return count;
    }

    static int leftCakeCount() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    static boolean validMap(int x, int y, boolean[][] visited) {
        return x >= 0 && x < N && y >= 0 && y < M && !visited[x][y];
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
