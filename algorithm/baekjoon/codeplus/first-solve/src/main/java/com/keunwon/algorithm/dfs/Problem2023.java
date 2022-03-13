package com.keunwon.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem2023 {

    static int N;
    static List<String> primes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        dfs("", 0);

        primes.stream().sorted().forEach(System.out::println);
    }

    static void dfs(String s, int depth) {
        if (depth == N) {
            primes.add(s);
            return;
        }

        for (int i = 1; i <= 9 ; i++) {
            int num = Integer.parseInt(s + i);

            if (isPrime(num)) {
                dfs(s + i, depth + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) { return false; }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) { return false; }
        }
        return true;
    }
}
