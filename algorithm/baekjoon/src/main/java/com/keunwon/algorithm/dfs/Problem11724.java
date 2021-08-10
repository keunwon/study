package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem11724 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int V;
    private static int[][] numbers;
    private static boolean[] checked;

    public static void main(String[] args) throws IOException {
        input();

        int count = 0;
        for (int i = 1; i <= V; i++) {
            if (!checked[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }
    
    private static void dfs(int x) {
        if (checked[x]) {
            return;
        }

        checked[x] = true;

        for (int i = 1; i <= V ; i++) {
            if (numbers[x][i] == 1) {
                dfs(i);
            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        numbers = new int[V + 1][V + 1];
        checked = new boolean[V + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            numbers[t1][t2] = numbers[t2][t1] = 1;
        }
    }
}
