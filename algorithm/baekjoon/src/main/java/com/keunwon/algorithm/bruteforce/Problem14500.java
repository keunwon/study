package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14500 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N, M;
    private static int[][] map;

    private static boolean[][] visited;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                otherShape(i, j);
            }
        }

        System.out.println(max);
    }

    private static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int[] move : moves) {
            final int nx = x + move[0];
            final int ny = y + move[1];

            if (validMap(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, sum + map[nx][ny], depth + 1);
                visited[nx][ny] = false;
            }
        }
    }

    private static void otherShape(int x, int y) {
        int min = Integer.MAX_VALUE;
        int sum = map[x][y];
        int count = 0;

        for (int[] move : moves) {
            final int nx = x + move[0];
            final int ny = y + move[1];

            if (!validMap(nx, ny)) { continue; }

            final int num = map[nx][ny];
            sum += num;
            min = Math.min(min, num);
            count++;
        }

        if (count < 3) { return; }
        else if (count == 4) { sum -= min; }
        max = Math.max(max, sum);
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
