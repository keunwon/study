package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem11559 {
    static final char[][] map = new char[12][6];
    static boolean[][] visited;

    static List<Position> list = new ArrayList<>();
    static final int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        int count = 0;
        boolean isMove = false;
        while (true) {
            visited = new boolean[12][6];
            isMove = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        list = new ArrayList<>();
                        bfs(i, j);

                        if (list.size() >= 4) {
                            isMove = true;

                            for (Position p : list) {
                                map[p.x][p.y] = '.';
                            }
                        }
                    }
                }
            }

            if (!isMove) { break; }

            movePuyos();
            count++;
        }

        System.out.println(count);
    }

    static void movePuyos() {
        for (int i = 0; i < 6; i++) {
            for (int j = 11; j > 0; j--) {
                if (map[j][i] == '.') {

                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '.') {
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static void bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        char color = map[x][y];

        queue.add(new Position(x, y));
        list.add(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && isGo(nx, ny, color)) {
                    queue.add(new Position(nx, ny));
                    list.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
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
