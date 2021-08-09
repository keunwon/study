package com.keunwon.algorithm.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Problem2812 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        char[] input = sc.next().toCharArray();

        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (K > 0 && !deque.isEmpty() && deque.getLast() < input[i]) {
                K--;
                deque.removeLast();
            }

            deque.addLast(input[i]);
        }

        StringBuilder sb = new StringBuilder();
        while (deque.size() > K) {
            sb.append(deque.removeFirst());
        }

        System.out.println(sb.toString());
    }
}
