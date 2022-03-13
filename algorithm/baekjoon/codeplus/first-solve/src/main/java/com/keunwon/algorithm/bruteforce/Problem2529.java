package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem2529 {
   private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   private static final List<String> result = new ArrayList<>();

   private static int N;
   private static char[] signs;
   private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        dfs("", 0);

        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));
    }

    private static void dfs(String num, int depth) {
        if (depth == N + 1) {
            result.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) { continue; }

            if (depth == 0 ||
                isFormula(signs[depth - 1], Character.getNumericValue(num.charAt(depth - 1)), i)) {
                visited[i] = true;
                dfs(num + i, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean isFormula(char sign, int x, int y) {
        if (sign == '<') { return x < y; }
        else if (sign == '>') { return x > y; }
        return false;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        signs = br.readLine().replaceAll(" ", "").toCharArray();
        visited = new boolean[10];
    }
}
