package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem6603 {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] selected;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int n = Integer.parseInt(st.nextToken());
            final int[] arr = new int[n];

            selected = new int[6];
            visited = new boolean[n];

            if (n == 0) { break; }

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(arr, 0, 0);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    public static void dfs(int[] arr, int start, int depth) throws IOException {
        if (depth == 6) {
            for (int i : selected) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        int size = arr.length;
        for (int i = start; i < size; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            selected[depth] = arr[i];
            dfs(arr, i, depth + 1);
            visited[i] = false;
        }
    }
}
