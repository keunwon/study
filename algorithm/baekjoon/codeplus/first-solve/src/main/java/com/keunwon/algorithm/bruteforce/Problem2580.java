package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem2580 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] map;
    private static int SIZE = 9;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
    }

    private static void dfs(int row, int col) throws IOException {
        if (col == SIZE) {
            dfs(row + 1, 0);
            return;
        }

        if (row == SIZE) {
            printMapWithSystemEnd();
        }

        if (map[row][col] != 0) {
            dfs(row, col + 1);
            return;
        }

        for (int i = 1; i <= SIZE; i++) {
            if (valid(row, col, i)) {
                map[row][col] = i;
                dfs(row, col + 1);
            }
        }
        map[row][col] = 0;
    }

    private static boolean valid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (map[row][i] == num || map[i][col] == num) {
                return false;
            }
        }

        final int rowBox = (row / 3) * 3;
        final int colBox = (col / 3) * 3;

        for (int i = rowBox; i < rowBox + 3; i++) {
            for (int j = colBox; j < colBox + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printMapWithSystemEnd() throws IOException {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                bw.write(map[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
        System.exit(0);
    }

    private static void input() throws IOException {
        map = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
