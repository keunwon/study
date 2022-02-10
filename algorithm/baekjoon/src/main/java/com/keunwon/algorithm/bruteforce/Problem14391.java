package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14391 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] numbers;
    private static boolean[][] visited;

    private static int max = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int x, int y) {
        if (x >= N) {
            totalSum();
            return;
        }

        if (y >= M) {
            dfs(x + 1, 0);
            return;
        }

        visited[x][y] = true;
        dfs(x, y + 1);

        visited[x][y] = false;
        dfs(x, y + 1);
    }

    private static void totalSum() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int colSum = 0;

            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    colSum *= 10;
                    colSum += numbers[i][j];
                    continue;
                }

                sum += colSum;
                colSum = 0;
            }
            sum += colSum;
        }

        for (int i = 0; i < M; i++) {
            int rowSum = 0;

            for (int j = 0; j < N; j++) {
                if (!visited[j][i]) {
                    rowSum *= 10;
                    rowSum += numbers[j][i];
                    continue;
                }

                sum += rowSum;
                rowSum = 0;
            }
            sum += rowSum;
        }

        max = Math.max(max, sum);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                numbers[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
    }
}
