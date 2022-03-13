package com.keunwon.algorithm.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1780 {
    private static int N;
    private static int[][] map;

    private static int GRAY = 0;
    private static int BLACK = 0;
    private static int WHITE = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0, N);

        System.out.println(GRAY);
        System.out.println(BLACK);
        System.out.println(WHITE);
    }

    private static void dfs(int x, int y, int size) {
        if (sameArea(x, y, size)) {
            final var color = map[x][y];

            if (color == -1) {
                GRAY++;
            } else if (color == 0) {
                BLACK++;
            } else if (color == 1) {
                WHITE++;
            }
            return;
        }

        final var reSize = size / 3;

        dfs(x, y, reSize);
        dfs(x, y + reSize, reSize);
        dfs(x, y + 2 * reSize, reSize);

        dfs(x + reSize, y, reSize);
        dfs(x + reSize, y + reSize, reSize);
        dfs(x + reSize, y + 2 * reSize, reSize);

        dfs(x + 2 * reSize, y, reSize);
        dfs(x + 2 * reSize, y + reSize, reSize);
        dfs(x + 2 * reSize, y + 2 * reSize, reSize);
    }

    private static boolean sameArea(int x, int y, int size) {
        final var color = map[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                final var st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }
}
