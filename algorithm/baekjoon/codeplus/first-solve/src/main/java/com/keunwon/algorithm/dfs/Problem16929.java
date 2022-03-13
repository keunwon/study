package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem16929 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int startR;
    static int startC;
    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (dfs(-1, -1, i, j, map[i][j])) {
                        System.out.println("Yes");
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println("No");
    }

    static boolean dfs(int preR, int preC, int r, int c, char color) {
        if (visited[r][c]) {
            return true;
        }

        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (!validMap(nr, nc)) { continue; }
            if (color != map[nr][nc]) { continue; }
            if (preR == nr && preC == nc) { continue; }


            if (dfs(r ,c, nr, nc, color)) {
                return true;
            }
        }
        return false;
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                map[i] = input;
            }
        }
    }
}
