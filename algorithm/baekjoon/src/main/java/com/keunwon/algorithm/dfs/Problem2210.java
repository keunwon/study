package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem2210 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] map = new int[5][5];
    static Set<String> words = new HashSet<>();
    static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 1, String.valueOf(map[i][j]));
            }
        }

        System.out.println(words.size());
    }

    static void dfs(int r, int c, int depth, String word) {
        if (depth == 6) {
            words.add(word);
            return;
        }

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc)) {
                dfs(nr, nc, depth + 1, word + map[nr][nc]);
            }
        }
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < 5 && c >=0 && c < 5;
    }

    static void input() throws IOException {
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
