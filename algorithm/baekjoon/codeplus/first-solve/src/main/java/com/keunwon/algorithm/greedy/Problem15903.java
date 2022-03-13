package com.keunwon.algorithm.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem15903 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int R = sc.nextInt();

        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.add(sc.nextLong());
        }

        for (int i = 0; i < R; i++) {
            long temp = queue.poll() + queue.poll();

            queue.add(temp);
            queue.add(temp);
        }

        long sum = 0L;
        while (queue.size() > 0) {
            sum += queue.poll();
        }

        System.out.println(sum);
    }
}
