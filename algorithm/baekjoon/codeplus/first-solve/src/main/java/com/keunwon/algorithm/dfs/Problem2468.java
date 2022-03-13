package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem2468 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final PriorityQueue<Integer> queue = new PriorityQueue<>();

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        input();

        int maxCount = 0;
        while (!queue.isEmpty()) {
            boolean[][] visited = new boolean[N][N];
            int h = queue.poll();
            int tempCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isGo(i, j, h, visited)) {
                        dfs(i, j, h, visited);
                        tempCount++;
                    }
                }
            }

            maxCount = Math.max(maxCount, tempCount);
        }

        System.out.println(maxCount);
    }

    private static void dfs(int r, int c, int height, boolean[][] visited) {
        visited[r][c] = true;

        int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc, height, visited)) {
                dfs(nr, nc, height, visited);
            }
        }
    }

    private static boolean isGo(int r, int c, int height, boolean[][] visited) {
        return map[r][c] > height && !visited[r][c];
    }

    private static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        queue.offer(0);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                queue.add(map[i][j]);
            }
        }
    }
}
