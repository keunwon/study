package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem13460 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static char[][] board;

    private static Ball startBall;
    private static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Ball>();

        queue.offer(startBall);
        visited[startBall.redX][startBall.redY][startBall.blueX][startBall.blueY] = true;

        while (!queue.isEmpty()) {
            var ball = queue.poll();

            if (10 <= ball.count) {
                System.out.println(-1);
                return;
            }

            for (int[] move : moves) {
                var bx = ball.blueX;
                var by = ball.blueY;
                var isBlueHome = false;

                while (board[bx + move[0]][by + move[1]] != '#') {
                    bx += move[0];
                    by += move[1];

                    if (board[bx][by] == 'O') {
                        isBlueHome = true;
                        break;
                    }
                }
                if (isBlueHome) { continue; }

                var rx = ball.redX;
                var ry = ball.redY;
                var isRedHole = false;

                while (board[rx + move[0]][ry + move[1]] != '#') {
                    rx += move[0];
                    ry += move[1];

                    if (board[rx][ry] == 'O') {
                        isRedHole = true;
                        break;
                    }
                }
                if (isRedHole) {
                    System.out.println(ball.count + 1);
                    return;
                }

                if (rx == bx && ry == by) {
                    if (move[0] == -1 && move[1] == 0) {
                        // 상
                        if (ball.redX > ball.blueX) { ++rx; }
                        else { ++bx; }
                    } else if (move[0] == 0 && move[1] == 1) {
                        // 우
                        if (ball.redY > ball.blueY) { --by; }
                        else { --ry; }
                    } else if (move[0] == 1 && move[1] == 0) {
                        // 하
                        if (ball.redX > ball.blueX) { --bx; }
                        else { --rx; }
                    } else if (move[0] == 0 && move[1] == -1) {
                        // 좌
                        if (ball.redY > ball.blueY) { ++ry; }
                        else { ++by; }
                    }
                }

                if (!visited[rx][ry][bx][by]) {
                    visited[rx][ry][bx][by] = true;
                    queue.offer(new Ball(rx, ry, bx, by, ball.count + 1));
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
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx= 0, ry =0, bx =0, by =0;
        for (int i = 0; i < N; i++) {
            var line = br.readLine();

            for (int j = 0; j < M; j++) {
                var w = line.charAt(j);
                board[i][j] = w;

                if ('R' == w) {
                    rx = i;
                    ry = j;
                } else if ('B' == w) {
                    bx = i;
                    by = j;
                }
            }
        }
        startBall = new Ball(rx, ry, bx, by, 0);
    }

    private static class Ball {
        final int redX;
        final int redY;
        final int blueX;
        final int blueY;
        final int count;

        Ball(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }
}
