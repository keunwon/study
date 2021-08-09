package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem11000 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] numbers = new int[N][2];

        for (int i = 0; i < N; i++) {
            numbers[i][0] = sc.nextInt();
            numbers[i][1] = sc.nextInt();
        }

        Arrays.sort(numbers, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (queue.isEmpty()) {
                count++;
                queue.add(numbers[i][1]);
                continue;
            }

            if (numbers[i][0] < queue.peek()) {
                count++;
            } else {
                queue.remove();
            }
            queue.add(numbers[i][1]);
        }

        System.out.println(count);
    }
}
