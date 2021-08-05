package com.keunwon.codingtest.chapter3;

import java.util.Arrays;
import java.util.Scanner;

public class Problem1015 {
    static int N;
    static Elem[] B;
    static int[] P;

    public static void main(String[] args) {
        input();

        Arrays.sort(B);

        for (int i = 0; i < N; i++) {
            System.out.println("B idx: " + B[i].idx + ", B num: " + B[i].num);
            P[B[i].idx] = i;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(P[i]);
        }
    }

    static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        B = new Elem[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = new Elem(i, sc.nextInt());
        }
    }

    static class Elem implements Comparable<Elem> {
        int idx;
        int num;

        public Elem(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Elem o) {
            if (num != o.num) { return num - o.num; }
            return idx - o.idx;
        }
    }
}
