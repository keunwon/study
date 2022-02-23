package com.keunwon.algorithm.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

public class Problem4574 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}};

    private static int N;
    private static int[][] sudoku;

    private static int count = 0;
    private static boolean[][] domino;

    public static void main(String[] args) throws IOException {
        while (true) {
            if (input()) {
                writeBanner();
                dfs(0);
                writeSudoku();
                continue;
            }

            bw.flush();
            System.exit(0);
        }
    }

    private static boolean dfs(int depth) {
        if (depth == 81) {
            return true;
        }

        final int x = depth / 9;
        final int y = depth % 9;

        if (sudoku[x][y] != 0) {
            return dfs(depth + 1);
        }

        for (int[] move : moves) {
            final int nx = x + move[0];
            final int ny = y + move[1];

            if (!validMap(nx, ny)) { continue; }

            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    if (i == j || domino[i][j]) {
                        continue;
                    }

                    if (check(x, y, i) && check(nx, ny, j)) {
                        sudoku[x][y] = i;
                        sudoku[nx][ny] = j;

                        domino[i][j] = true;
                        domino[j][i] = true;

                        if (dfs(depth + 1)) {
                            return true;
                        }

                        sudoku[x][y] = 0;
                        sudoku[nx][ny] = 0;

                        domino[i][j] = false;
                        domino[j][i] = false;
                    }
                }
            }
        }

        return false;
    }

    private static boolean check(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][y] == num) {
                return false;
            }
        }

        final int row = (x / 3) * 3;
        final int col = (y / 3) * 3;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < 9 && 0 <= y && y < 9 && sudoku[x][y] == 0;
    }

    private static void writeSudoku() throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(String.valueOf(sudoku[i][j]));
            }
            bw.newLine();
        }
    }

    private static void writeBanner() throws IOException {
        count++;
        bw.write("Puzzle " + count);
        bw.newLine();
    }

    private static boolean input() throws IOException {
        N = Integer.parseInt(br.readLine());
        sudoku = new int[9][9];
        domino = new boolean[10][10];

        if (N == 0) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int U = Integer.parseInt(st.nextToken());
            final String LU = st.nextToken();

            final int V = Integer.parseInt(st.nextToken());
            final String LV = st.nextToken();

            sudoku[LU.charAt(0) - 'A'][LU.charAt(1) - '1'] = U;
            sudoku[LV.charAt(0) - 'A'][LV.charAt(1) - '1'] = V;

            domino[U][V] = true;
            domino[V][U] = true;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 9; i++) {
            String s = st.nextToken();
            sudoku[s.charAt(0) - 'A'][s.charAt(1) - '1'] = i;
        }

        return true;
    }
}
