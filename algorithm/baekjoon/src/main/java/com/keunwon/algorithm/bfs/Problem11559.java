package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Problem11559 {
    static final char[][] map = new char[12][6];
    static boolean[][] visited;

    static final int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        boolean isMove;
        int count = 0;
        while (true) {
            visited = new boolean[12][6];
            isMove = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {
                        if (!visited[i][j] && bfs(i, j)) {
                            isMove = true;
                        }
                    }
                }
            }

            if (isMove) {
                moveColor();
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    static boolean bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        char color = map[x][y];
        int count = 1;

        queue.add(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && isGo(nx, ny, color)) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        return count >= 4;
    }

    static void moveColor() {
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 6; j++) {
//                if (visited[i][j]) {
//                    move(i, j);
//                }
//            }
//        }

        for (int i = 0; i < ; i++) {
            
        }
    }

    static void move(int x, int y) {
        if (map[x][y] == '.') { return; }

        if (!validMap(x - 1, y)) { return; }

        map[x][y] = map[x - 1][y];
        move(x - 1, y);
    }

    static boolean isGo(int x, int y, char color) {
        return map[x][y] == color && !visited[x][y];
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
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
