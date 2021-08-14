package com.keunwon.algorithm.dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1941 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter brw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final char[][] map = new char[5][5];

    static final int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static int[] selected;
    static boolean[][] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void solution() {
        selected = new int[7];
        visited = new boolean[5][5];

        dfs(0, 0, 0);
        System.out.println(result);
    }

    static void dfs(int num, int depth, int sCount) {
        if (depth == 7) {
            if (sCount >= 4) {
                bfs();
            }
            return;
        }

        for (int i = num; i < 25; i++) {
            selected[depth] = i;
            visited[i / 5][i % 5] = true;


            if (map[i /5][i % 5] == 'S') {
                dfs(i + 1, depth + 1, sCount + 1);
            } else {
                dfs(i + 1, depth + 1, sCount);
            }

            visited[i / 5][i % 5] = false;
        }
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] checked = new boolean[5][5];

        queue.add(selected[0]);

        int count = 0;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            int row = n / 5;
            int column = n % 5;

            for (int[] move : moves) {
                int nr = row + move[0];
                int nl = column + move[1];
                int moveN = nr * 5 + nl;

                if (validMap(nr, nl) && visited[nr][nl] && !checked[nr][nl]) {
                    checked[nr][nl] = true;
                    queue.add(moveN);
                    count++;
                }
            }
        }

        if (count == 7) {
            result++;
        }
    }

    static boolean validMap(int r, int c) {
        return r >=0 && r < 5 && c >= 0 && c < 5;
    }

    static void input() throws IOException {
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }
    }
}
