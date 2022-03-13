package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2644 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] targetNumbers = new int[2];
    static int[][] numbers;
    static boolean[] visited;
    static int count = -1;

    public static void main(String[] args) throws IOException {
        input();

        dfs(targetNumbers[0], 1);
        System.out.println(count);
    }

    static void dfs(int x, int depth) {
        visited[x] = true;

        for (int i = 1; i < N + 1; i++) {
            if (numbers[x][i] == 1 && !visited[i]) {
                if (i == targetNumbers[1]) {
                    count = depth;
                    return;
                }
                dfs(i, depth + 1);
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        targetNumbers[0] = Integer.parseInt(st.nextToken());
        targetNumbers[1] = Integer.parseInt(st.nextToken());

        int r = Integer.parseInt(br.readLine());
        numbers = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());

            numbers[temp1][temp2] = numbers[temp2][temp1] = 1;
        }
    }
}
