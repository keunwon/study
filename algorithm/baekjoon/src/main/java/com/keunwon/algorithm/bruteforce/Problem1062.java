package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1062 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, K;
    private static String[] words;

    private static int max = 0;
    private static int mask;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, mask, 0);
        System.out.println(max);
    }

    private static void dfs(int index, int mask, int depth) {
        if (K - 5 == depth) {
            int count = 0;

            for (int i = 0; i < N; i++) {
                boolean flag = true;

                for (int j = 0; j < words[i].length(); j++) {
                    if ((mask & (1 << words[i].charAt(j) - 96)) == 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) { count++; }
            }

            max = Math.max(max, count);
            return;
        }

        for (int i = index; i <= 26; i++) {
            if ((mask & (1 << i)) == 0) {
                dfs(i + 1, mask | (1 << i), depth + 1);
            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine().replaceAll("[antic]", "");
            words[i] = word;
        }

        mask |= 1 << (int) 'a' - 96;
        mask |= 1 << (int) 'n' - 96;
        mask |= 1 << (int) 't' - 96;
        mask |= 1 << (int) 'i' - 96;
        mask |= 1 << (int) 'c' - 96;
    }
}
