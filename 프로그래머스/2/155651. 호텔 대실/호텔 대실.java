import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] book_time) {
        var list = Arrays.stream(book_time)
                .map(times -> new int[] {toMinute(times[0]), toMinute(times[1]) + 10})
                .sorted(Comparator.comparingInt(o -> o[0]))
                .toList();
        var q = new PriorityQueue<Integer>(list.size());

        for (int[] times : list) {
            if (!q.isEmpty() && q.peek() <= times[0]) {
                q.poll();
            }
            q.offer(times[1]);
        }
        return q.size();
    }

    private int toMinute(String time) {
        var hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return hour * 60 + minute;
    }
}