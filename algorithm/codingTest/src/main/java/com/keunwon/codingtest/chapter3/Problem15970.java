package com.keunwon.codingtest.chapter3;

import java.util.*;

public class Problem15970 {
    static int N;
    static List<List<Integer>> A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            A.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int location = sc.nextInt();
            int color = sc.nextInt();

            A.get(color).add(location);
        }

        for (int i = 0; i < 2; i++) {
            Collections.sort(A.get(i));
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < A.get(i).size(); j++) {
                if (j == 0) {
                    result += A.get(i).get(j + 1) - A.get(i).get(j);
                } else if (j == A.get(i).size() - 1) {
                    result += A.get(i).get(j) - A.get(i).get(j - 1);
                } else {
                    int left = A.get(i).get(j) - A.get(i).get(j - 1);
                    int right = A.get(i).get(j + 1) - A.get(i).get(j);

                    result += Math.min(left, right);
                }
            }
        }

        System.out.println(result);
    }

}
