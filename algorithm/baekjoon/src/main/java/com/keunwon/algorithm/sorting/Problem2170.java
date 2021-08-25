package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem2170 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(points);

        int x = points.get(0).x;
        int y = points.get(0).y;
        int ans = y - x;

        for (int i = 1; i < N; i++) {
            int nx = points.get(i).x;
            int ny = points.get(i).y;

           if (x <= nx && ny <= y) {
               continue;
           } else if (nx < y) {
               ans += ny - y;
           } else {
               ans += ny - nx;
           }

           x = nx;
           y = ny;
        }

        System.out.println(ans);
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (x == o.x) {
                return y - o.y;
            }
            return x - o.x;
        }
    }
}
