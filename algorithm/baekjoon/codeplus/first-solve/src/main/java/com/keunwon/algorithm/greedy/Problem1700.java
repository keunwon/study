package com.keunwon.algorithm.greedy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem1700 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] numbers = new int[K];
        for (int i = 0; i < K; i++) {
            numbers[i] = sc.nextInt();
        }

        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < K; i++) {
            if (set.size() < N) {
                set.add(numbers[i]);
                continue;
            } else if (set.contains(numbers[i])) {
                continue;
            }

            int key = -1;
            int idx = -1;
            for (Integer value : set) {
                int tempIdx = -1;
                for (int j = i + 1; j < K; j++) {
                    if (value == numbers[j]) {
                        tempIdx = j;
                        break;
                    }
                }

                if (tempIdx == -1) {
                    key = value;
                    break;
                }
                idx = Math.max(idx, tempIdx);
                key = numbers[idx];
            }

            count++;
            set.remove(key);
            set.add(numbers[i]);
        }

        System.out.println(count);
    }
}
