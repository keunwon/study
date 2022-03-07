package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem1377 {
    private static final List<Point> points = new ArrayList<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        input();
        Collections.sort(points);

        System.out.println(maxIndex() + 1);
    }

    private static int maxIndex() {
        var max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, points.get(i - 1).index - i);
        }
        return max;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            N = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
                final var num = Integer.parseInt(br.readLine());
                points.add(new Point(num, i));
            }
        }
    }

    private static class Point implements Comparable<Point> {
        final int num;
        final int index;

        Point(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Point o) {
            if (num == o.num) {
                return index - o.index;
            }
            return num - o.num;
        }
    }
}
