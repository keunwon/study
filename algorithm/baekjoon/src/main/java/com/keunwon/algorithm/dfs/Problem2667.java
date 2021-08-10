package com.keunwon.algorithm.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem2667 {

    static int N;
    static int[][] map;
    static boolean[][] checked;
    private static int count = 0;

    public static void main(String[] args) {
        input();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !checked[i][j]) {
                    count = 1;
                    dfs(i, j);
                    list.add(count);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        list.forEach(System.out::println);
    }

    private static void dfs(int r, int c) {
        checked[r][c] = true;

        int[][] move = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        for (int[] ints : move) {
            int nr = r + ints[0];
            int nc = c + ints[1];

            if (validMap(nr, nc) && isGo(nr, nc)) {
                dfs(nr, nc);
                count++;
            }
        }
    }

    private static boolean validMap(int r, int x) {
        return r >= 0 && r < N && x >= 0 && x < N;
    }

    private static boolean isGo(int r, int c) {
        return map[r][c] == 1 && !checked[r][c];
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        checked = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String input = sc.next();

            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }
}

