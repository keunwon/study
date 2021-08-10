package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem1987 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int row;
    static int column;
    static char[][] map;

    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static Set<Character> visited = new HashSet<>();
    static int maxDepth = 0;

    public static void main(String[] args) throws IOException {
        input();

        dfs(0, 0, 1);
        System.out.println(maxDepth);
    }

    static void dfs(int r, int c, int depth) {
        visited.add(map[r][c]);
        maxDepth = Math.max(maxDepth, depth);

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && isGo(nr, nc)) {
                dfs(nr, nc, depth + 1);
                visited.remove(map[nr][nc]);
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    static boolean isGo(int r, int c) {
        return !visited.contains(map[r][c]);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        map = new char[row][column];

        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().replace(" ", "").toCharArray();
        }
    }
}
