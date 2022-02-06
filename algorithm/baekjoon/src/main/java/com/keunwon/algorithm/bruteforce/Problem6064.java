package com.keunwon.algorithm.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class Problem6064 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int M = Integer.parseInt(st.nextToken());
            final int N = Integer.parseInt(st.nextToken());
            final int x = Integer.parseInt(st.nextToken());
            final int y = Integer.parseInt(st.nextToken());

            final int lcm = lcm(M, N);
            int result = -1;
            for (int j = x; j < lcm; j += M) {
                int reminder = (j % N == 0) ? N : j % N;

                if (reminder == y) {
                    result = j;
                    break;
                }
            }

            System.out.println(result);
        }
    }

    private static int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    private static int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }
}
