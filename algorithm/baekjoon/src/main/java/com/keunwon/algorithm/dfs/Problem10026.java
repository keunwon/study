package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiPredicate;

public class Problem10026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[][] map;
    static boolean[][] visited;

    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) throws IOException {
        input();

        solution((c1, c2) -> c1 == c2);
        solution(Problem10026::colorBlindness);
    }

    static void solution(BiPredicate<Character, Character> sameColor) {
        visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isGo(i, j) && sameColor.test(map[i][j], map[i][j])) {
                    dfs(i, j, sameColor);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void dfs(int r, int c, BiPredicate<Character, Character> sameColor) {
        visited[r][c] = true;
        char preColor = map[r][c];

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc) && sameColor.test(preColor, map[nr][nc])) {
                dfs(nr, nc, sameColor);
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static boolean isGo(int r, int x) {
        return !visited[r][x];
    }

    static boolean colorBlindness(char preColor, char color) {
        if (preColor != 'B' && color != 'B') {
            return true;
        }
        return preColor == color;
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
