package algorithm.programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lesson17678 {
    public String solution(int n, int t, int m, String[] timetable) {
        var queue = new PriorityQueue<Integer>();
        var startTime = toMinutes("09:00");
        var bus = new ArrayList<Integer>();

        for (var time : timetable) {
            queue.offer(toMinutes(time));
        }

        for (int i = 0; i < n; i++) {
            bus.clear();

            while (!queue.isEmpty() && bus.size() < m) {
                if (queue.peek() <= startTime) {
                    bus.add(queue.poll());
                } else {
                    break;
                }
            }

            if (i == n - 1) {
                if (bus.size() < m) break;
                else if (bus.size() == m) {
                    var last = bus.get(bus.size() - 1);
                    startTime = last - 1;
                    break;
                }
            }
            startTime += t;
        }
        return String.format("%02d:%02d", startTime / 60, startTime % 60);
    }

    private int toMinutes(String time) {
        var arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}
