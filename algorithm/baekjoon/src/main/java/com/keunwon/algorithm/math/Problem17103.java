package com.keunwon.algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem17103 {
    static final int N_SIZE = 1_000_000;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final boolean[] primes = createPrimesArray();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            final int N = Integer.parseInt(br.readLine());
            int count = 0;

            for (int i = 2; i <= N / 2; i++) {
                if (!primes[i] && !primes[N - i]) {
                    count++;
                }
            }

            sb.append(count).append(System.lineSeparator());
        }

        System.out.print(sb.toString());
    }

    static boolean[] createPrimesArray() {
        boolean[] result = new boolean[N_SIZE + 1];

        for (int i = 2; i <= Math.sqrt(N_SIZE); i++) {
            for (int j = i * 2; j < N_SIZE; j += i) {
                result[j] = true;
            }
        }

        return result;
    }
}
