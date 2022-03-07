package com.keunwon.algorithm.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1074 {
    private static int N;
    private static int r, c;

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(r, c, getSize(N));

        System.out.println(count);
    }

    private static void dfs(int x, int y, int size) {
        if (size == 1) { return; }

        final var resize = size / 2;
        final var num = size * size / 4;

        if (x < resize && y < resize) {
            dfs(x, y, resize);
        } else if (x < resize) {
            count += num;
            dfs(x, y -resize, resize);
        } else if (y < resize) {
            count += num * 2;
            dfs(x - resize, y, resize);
        } else {
            count += num * 3;
            dfs(x - resize, y - resize, resize);
        }
    }

    private static int getSize(int num) {
        return (int) (Math.pow(2, num));
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            final var st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }
    }
}
