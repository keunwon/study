import java.util.PriorityQueue;

import static java.util.Comparator.reverseOrder;

class Solution {
    public long solution(int n, int[] works) {
        var q = new PriorityQueue<Integer>(reverseOrder());
        var totalWork = 0;

        for (var w : works) {
            q.offer(w);
            totalWork += w;
        }

        if (totalWork <= n) {
            return 0;
        }

        for (var i = 0; i < n; i++) {
            var w = q.poll();
            if (w == 0) break;
            q.offer(w - 1);
        }

        var res = 0L;
        while (!q.isEmpty()) {
            var w = q.poll();
            res += (long) w * w;
        }
        return res;
    }
}