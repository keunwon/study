package com.keunwon.algorithm.programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lesson17678_2 {
    public String solution(int n, int t, int m, String[] timetable) {
        var q = new PriorityQueue<Integer>();
        var busStop = seconds("09:00");
        var waitTimes = new ArrayList<Integer>();
        var result = 0;

        for (var time : timetable) {
            q.offer(seconds(time));
        }

        for (var i = 0; i < n; i++) {
            waitTimes.clear();

            while (!q.isEmpty() && waitTimes.size() < m && q.peek() <= busStop) {
                waitTimes.add(q.poll());
            }

            if (i == n - 1) {
                if (waitTimes.size() == m) {
                    result = waitTimes.get(waitTimes.size() - 1) - 1;
                } else {
                    result = busStop;
                }
                break;
            }

            busStop += t;
        }

        return String.format("%02d:%02d", result / 60, result % 60);
    }

    private int seconds(String time) {
        var arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    public static void main(String[] args) {
        var timetable = new String[]{"09:10", "09:09", "08:00"};
        var result = new Lesson17678_2().solution(2, 10, 2, timetable);
        System.out.println(result); // 09:09
    }
}
