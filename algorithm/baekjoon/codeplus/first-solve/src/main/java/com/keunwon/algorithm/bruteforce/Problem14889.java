package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14889 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int N;
    public static int[][] points;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(min);
    }

    public static void dfs(int start, int depth) {
        if (N / 2 == depth) {
            calc();
            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    public static void calc() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startTeam += points[i][j];
                    startTeam += points[j][i];
                } else if (!visited[i] && !visited[j]) {
                    linkTeam += points[i][j];
                    linkTeam += points[j][i];
                }
            }
        }

        min = Math.min(min, Math.abs(startTeam - linkTeam));
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        points = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                points[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
