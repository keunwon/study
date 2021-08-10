package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1260 {

    private static int V;
    private static int E;
    private static int S;
    private static int[][] nodes;
    private static boolean[] checked;

    public static void main(String[] args) throws IOException {
        input();

        dfs(S);
        resetChecked();
        bfs();
    }

    private static void dfs(int x) {
        System.out.print(x + " ");
        
        checked[x] = true;

        for (int i = 1; i < V + 1; i++) {
            if (nodes[x][i] == 1 && !checked[i]) {
                dfs(i);
            }
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        checked[S] = true;

        System.out.print(System.lineSeparator() + S + " ");

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int i = 0; i < V + 1; i++) {
                if (nodes[temp][i] == 1 && !checked[i]) {
                    queue.add(i);
                    checked[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }

    private static void resetChecked() {
        checked = new boolean[V + 1];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nodes = new int[V + 1][V + 1];
        checked = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = 1; j < V + 1; j++) {
                if (j == y) {
                    nodes[x][j] = nodes[j][x] = 1;
                }
            }
        }
    }
}
