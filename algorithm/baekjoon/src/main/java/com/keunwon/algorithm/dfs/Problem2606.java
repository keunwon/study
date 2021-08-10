package com.keunwon.algorithm.dfs;

import java.util.Scanner;

public class Problem2606 {
    private static int V;
    private static int E;
    private static int[][] map;
    private static boolean[] checked;
    private static int count = 0;

    public static void main(String[] args) {
        input();


        for (int i = 1; i < V + 1; i++) {
            if (map[1][i] == 1 && !checked[i]) {
                dfs(map[1][i]);
            }
        }

        System.out.println(count);
    }

    private static void dfs(int x) {
        checked[x] = true;

        for (int i = 1; i < V + 1; i++) {
            if (map[x][i] == 1 && !checked[i]) {
                count++;
                dfs(i);
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        map = new int[V + 1][V + 1];
        checked = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            map[v1][v2] = map[v2][v1] = 1;
        }
    }
}
