package com.keunwon.algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1934 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = a > b ? dfs(a ,b) : dfs(b, a);

            System.out.println(a * b / c);
        }
    }

    static int dfs(int a, int b) {
        if (b == 0) {
            return a;
        }
        return dfs(b, a % b);
    }
}
