package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem12904 {
    private static String S, T;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(dfs(T) ? 1 : 0);
    }

    private static boolean dfs(String str) {
        if (S.length() == str.length()) {
            return S.equals(str);
        }

        if (str.endsWith("A")) {
            final var word = str.substring(0, str.length() - 1);

            if (dfs(word)) { return true; }
        }

        if (str.startsWith("B")) {
            final var sb = new StringBuilder(str);

            sb.deleteCharAt(0);
            sb.reverse();

            return dfs(sb.toString());
        }
        return false;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            S = br.readLine();
            T = br.readLine();
        }
    }
}
