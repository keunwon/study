package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem10974 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] arr;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();

        arr = new int[N];
        visited = new boolean[N];

        dfs(N, 0);
        bw.flush();
        bw.close();
    }

    public static void dfs(int n, int depth) throws IOException {
        if (n == depth) {
            for (int i : arr) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            arr[depth] = i + 1;
            dfs(n, depth + 1);
            visited[i] = false;
        }
    }
}
