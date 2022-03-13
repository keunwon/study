package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2109 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int n;
    private static Queue<Lecture> lectures;

    public static void main(String[] args) throws IOException {
        input();

        var visited = new boolean[10001];
        var result = 0;

        while (!lectures.isEmpty()) {
            var lecture = lectures.poll();

            for (int i = lecture.day; i > 0; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    result += lecture.money;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        lectures = new PriorityQueue<Lecture>();

        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            var p = Integer.parseInt(st.nextToken());
            var d = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(p, d));
        }
    }

    private static class Lecture implements Comparable<Lecture> {
        final int money;
        final int day;

        Lecture(int money, int day) {
            this.money = money;
            this.day = day;
        }

        @Override
        public int compareTo(Lecture o) {
            if (money == o.money) {
                return day - o.day;
            }
            return o.money - money;
        }
    }
}
