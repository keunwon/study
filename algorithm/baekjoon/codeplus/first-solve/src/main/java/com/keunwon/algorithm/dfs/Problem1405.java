package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1405 {
    static int N;
    static boolean[][] visited = new boolean[30][30];
    static double[] way = new double[4];
    static int[][] moves = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    static double result = 0.0;

    public static void main(String[] args) throws IOException {
        input();

        dfs(15, 15, 0, 1.0);
        System.out.println(result);
    }

    static void dfs(int r, int c, int depth, double percent) {
        if (depth == N) {
            result += percent;
            return;
        }

        visited[r][c] = true;

        for (int i = 0; i < moves.length; i++) {
            int nr = r + moves[i][0];
            int nc = c + moves[i][1];

            if (validMap(nr, nc ) && isGo(nr, nc, way[i])) {
                System.out.println("run dfs");
                dfs(nr, nc, depth + 1, percent * way[i]);
                visited[nr][nc] = false;
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < 30 && c >=0 && c < 30;
    }

    static boolean isGo(int r, int c, double way) {
        return !visited[r][c] && way > 0;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        for (int i = 0; i < 4; i++) {
            way[i] = Double.parseDouble(input[i + 1]) * 0.01;
        }
    }
}
