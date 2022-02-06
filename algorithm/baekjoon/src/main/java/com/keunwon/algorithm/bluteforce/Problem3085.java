package com.keunwon.algorithm.bluteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem3085 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, rowCount(map, i));
            max = Math.max(max, colCount(map, i));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 행
                if (i + 1 < n) {
                    swap(map, i, j, i + 1, j);
                    max = Math.max(max, rowCount(map, i));
                    max = Math.max(max, rowCount(map, i + 1));
                    max = Math.max(max, rowCount(map, j));
                    swap(map, i, j, i + 1, j);
                }

                // 열
                if (j + 1 < n) {
                    swap(map, i, j, i, j + 1);
                    max = Math.max(max, rowCount(map, i));
                    max = Math.max(max, colCount(map, j));
                    max = Math.max(max, colCount(map, j + 1));
                    swap(map, i, j, i, j + 1);
                }
            }
        }

        System.out.println(max);
    }

    private static int rowCount(char[][] map, int x) {
        int result = 1;
        int temp = 1;
        char word = map[x][0];

        for (int i = 1; i < map.length; i++) {
            if (word == map[x][i]) {
                temp++;
                continue;
            }

            word = map[x][i];
            result = Math.max(result, temp);
            temp = 1;
        }
        return Math.max(result, temp);
    }

    private static int colCount(char[][] map, int y) {
        int result = 1;
        int temp = 1;
        char word = map[0][y];

        for (int i = 1; i < map.length; i++) {
            if (word == map[i][y]) {
                temp++;
                continue;
            }

            word = map[i][y];
            result = Math.max(result, temp);
            temp = 1;
        }
        return Math.max(result, temp);
    }

    private static void swap(char[][] map, int x1, int y1, int x2, int y2) {
        final char temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }
}
