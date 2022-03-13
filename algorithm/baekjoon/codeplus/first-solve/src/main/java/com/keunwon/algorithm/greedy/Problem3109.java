package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem3109 {
    static int[] move = {-1, 0, 1};
    static char[][] map;
    static int row;
    static int column;

    public static void main(String[] args) {
        input();

        int count = 0;
        for (int i = 0; i < row; i++) {
            if (go(i, 0)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean go(int r, int c) {
        map[r][c] = 'x';

        if (c == column - 1) {
            return true;
        }

        for (int i = 0; i < move.length; i++) {
            int mr = r + move[i];
            int mc = c + 1;

            if (mr < 0 || mr >= row || map[mr][mc] == 'x') {
                continue;
            }

            if (go(mr, mc)) {
                return true;
            }
        }
        return false;
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        column = sc.nextInt();
        map = new char[row][column];

        for (int i = 0; i < row; i++) {
            String word = sc.next();

            for (int j = 0; j < column; j++) {
                map[i][j] = word.charAt(j);
            }
        }
    }
}
