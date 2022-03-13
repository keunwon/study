package com.keunwon.algorithm.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem1715 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextLong());
        }

        long num = 0L;
        while (queue.size() > 1) {
            long a = queue.poll();
            long b = queue.poll();

            num += a + b;
            queue.add(a + b);
        }

        System.out.println(num);
    }
}
