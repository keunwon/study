package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem3055 {
    static int N, M;
    static char[][] map;

    static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static Queue<Position> waterQueue = new LinkedList<>();
    static Queue<Position> beaverQueue = new LinkedList<>();
    static boolean[][] visited;
    static int minSecond = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        int second = 0;
        while (!beaverQueue.isEmpty()) {
            moveWater();
            moveBeaver(++second);
        }

        if (minSecond == Integer.MAX_VALUE) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(minSecond);
        }
    }

    // 1. 물을 채운다
    // - 비어있는 칸으로 이동
    // - 돌을 통과 할 수 없음
    // - 비보 굴을 통과 할 수 없음
    // 2. 비버를 이동한다
    // - 인접한 곳 하나로 이동
    // - 돌을 통과 할 수 없음
    // 3. 반복하여 비버 굴까지 이동
    static void moveWater() {
        int size = waterQueue.size();

        for (int i = 0; i < size; i++) {
            Position p = waterQueue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && isEmptyArea(nx, ny)) {
                    waterQueue.add(new Position(nx, ny));
                    map[nx][ny] = '*';
                }
            }
        }
    }

    static void moveBeaver(int second) {
        int size = beaverQueue.size();

        while (size -- > 0) {
            Position p = beaverQueue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (!validMap(nx, ny) || !isGo(nx, ny)) {
                    continue;
                }

                if (isFinish(nx, ny)) {
                    minSecond = Math.min(minSecond, second);
                    continue;
                }

                if (!visited[nx][ny]) {
                    beaverQueue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isFinish(int x, int y) {
        return map[x][y] == 'D';
    }

    static boolean isGo(int x, int y) {
        return isEmptyArea(x, y) || map[x][y] == 'D';
    }

    static boolean isEmptyArea(int x, int y) {
        return map[x][y] == '.';
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >=0 && y < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == '*') {
                    waterQueue.add(new Position(i, j));
                } else if (map[i][j] == 'S') {
                    beaverQueue.add(new Position(i, j));
                }
            }
        }

        br.close();
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
