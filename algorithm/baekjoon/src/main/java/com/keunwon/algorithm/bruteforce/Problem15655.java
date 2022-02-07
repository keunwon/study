package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Problem15655 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] numbers;
    public static boolean[] visited;
    public static int[] result;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();

        numbers = new int[N];
        visited = new boolean[N];
        result = new int[M];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        dfs(N, M, 0);

        bw.flush();
        bw.close();
    }

    public static void dfs(int n, int m, int depth) throws IOException {
        if (m == depth) {
            for (int i : result) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            result[depth] = numbers[i];
            dfs(n, m, depth + 1);

            for (int j = i + 1; j < n; j++) {
                visited[j] = false;
            }
        }
    }
}
