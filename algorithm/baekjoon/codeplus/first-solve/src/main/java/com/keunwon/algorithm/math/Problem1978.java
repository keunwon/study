package com.keunwon.algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1978 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] primes = createPrimesArray();
        int count = 0;
        while (N-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            if (!primes[num]) {
                count++;
            }
        }

        System.out.println(count);
    }

   static boolean[] createPrimesArray() {
        boolean[] result = new boolean[1001];

       for (int i = 2; i <= Math.sqrt(1000); i++) {

           for (int j = i * 2; j <= 1000; j += i) {
               result[j] = true;
           }
       }
       return result;
   }
}
