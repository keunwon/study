package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem10775 {
    private static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int G = sc.nextInt();
        int P = sc.nextInt();

        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;
        for (int i = 0; i < P; i++) {
            int findValue = find(sc.nextInt());

            if (findValue == 0) {
                break;
            }

            union(findValue, findValue - 1);
            count++;
        }

        System.out.println(count);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) { return; }

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
