package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Problem16954 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {0, 0}};

    private static char[][] board;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Position>();
        queue.offer(new Position(7, 0));

        while (!queue.isEmpty()) {
            var size = queue.size();

            for (int i = 0; i < size; i++) {
                var p = queue.poll();

                if (board[p.x][p.y] == '#') { continue; }

                for (int[] move : moves) {
                    var nx = p.x + move[0];
                    var ny = p.y + move[1];

                    if (!validMap(nx, ny)) { continue; }

                    if (nx == 0 && ny == 7) {
                        System.out.println(1);
                        return;
                    }

                    if (board[nx][ny] != '#') {
                        queue.offer(new Position(nx, ny));
                    }
                }
            }

            down();
        }

        System.out.println(0);
    }

    private static void down() {
        for (int i = 7; i >= 1; i--) {
            System.arraycopy(board[i - 1], 0, board[i], 0, 8);
        }

        for (int i = 0; i < 8; i++) {
            board[0][i] = '.';
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    private static void input() throws IOException {
        board = new char[8][8];

        for (int i = 0; i < 8; i++) {
            var line = br.readLine();

            for (int j = 0; j < 8; j++) {
                board[i][j] = line.charAt(j);
            }
        }
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
