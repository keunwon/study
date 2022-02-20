package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem16197 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static char[][] map;

    private static Coin startCoin;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        dfs(startCoin, 0);

        System.out.println(10 < min ? -1 : min);
    }

    private static void dfs(Coin coin, int depth) {
        if (min < depth || 10 < depth) {
            return;
        }

        for (int[] move : moves) {
            int nx1 = coin.x1 + move[0];
            int ny1 = coin.y1 + move[1];
            int nx2 = coin.x2 + move[0];
            int ny2 = coin.y2 + move[1];

            int overCount = 0;
            if (overMap(nx1, ny1)) { overCount++; }
            if (overMap(nx2, ny2)) { overCount++; }

            if (overCount == 2) { continue; }

            if (overCount == 1) {
                min = Math.min(min, depth + 1);
                return;
            }

            if (map[nx1][ny1] == '#') {
                nx1 = coin.x1;
                ny1 = coin.y1;
            }

            if (map[nx2][ny2] == '#') {
                nx2 = coin.x2;
                ny2 = coin.y2;
            }

            dfs(new Coin(nx1, ny1, nx2, ny2), depth + 1);
        }
    }

    private static boolean overMap(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new  char[N][M];

        boolean isFirst = true;
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'o') {
                    if (isFirst) {
                        x1 = i;
                        y1 = j;
                        isFirst = false;
                        continue;
                    }
                    x2 = i;
                    y2 = j;
                }
            }
        }

        startCoin = new Coin(x1, y1, x2, y2);
    }

    private static class Coin {
        final int x1;
        final int y1;

        final int x2;
        final int y2;

        Coin(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
