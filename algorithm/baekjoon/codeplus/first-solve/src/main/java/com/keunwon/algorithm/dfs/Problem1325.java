package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1325 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int M;
    static List<Integer>[] list;

    static int[] ans;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 1; i <= N ; i++) {
            dfs(i, new boolean[N + 1]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, ans[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (max == ans[i]) {
                bw.write(i + " ");
            }
        }

        bw.flush();
        br.close();
    }

    static void dfs(int x, boolean[] visited) {
        visited[x] = true;

        for (Integer num : list[x]) {
            if (!visited[num]) {
                ans[num]++;
                dfs(num, visited);
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        ans = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            list[num1].add(num2);
        }
    }
}
