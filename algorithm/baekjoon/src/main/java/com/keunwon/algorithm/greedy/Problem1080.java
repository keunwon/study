package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem1080 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int width;
    static int height;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        int[][] matrixA = new int[height][width];
        int[][] matrixB = new int[height][width];

        input(matrixA);
        input(matrixB);

        int ans = 0;
        if (width < 3 || height < 3) {
            System.out.println( Arrays.deepEquals(matrixA, matrixB) ? ans : -1);
            return;
        }

        for (int i = 0; i < height - 2; i++) {
            for (int j = 0; j < width - 2; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    togglePartialMatrix(matrixA, i, j);
                    ans++;
                }
            }
        }

        System.out.println(Arrays.deepEquals(matrixA, matrixB) ? ans : -1);
    }

    private static void togglePartialMatrix(int[][] matrixA, int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                matrixA[i][j] = (matrixA[i][j] == 0) ? 1 : 0;
            }
        }
    }

    private static void input(int[][] matrix) throws IOException {
        for (int i = 0; i < height; i++) {
            String row = br.readLine();

            for (int j = 0; j < width; j++) {
                matrix[i][j] = Integer.parseInt(row.substring(j, j + 1));
            }
        }
    }
}
