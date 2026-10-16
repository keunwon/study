import java.util.ArrayDeque;
import java.util.PriorityQueue;

import static java.util.Comparator.reverseOrder;

class Solution {
    public int solution(int[] priorities, int location) {
        var pq = new PriorityQueue<Integer>(reverseOrder());
        var deque = new ArrayDeque<int[]>(priorities.length);

        for (var i = 0; i < priorities.length; i++) {
            var p = priorities[i];
            pq.offer(p);
            deque.addLast(new int[] {p, i});
        }

        var res = 0;
        while (!pq.isEmpty()) {
            var max = pq.poll();
            ++res;

            while (true) {
                var cur = deque.removeFirst();
                if (cur[0] == max) {
                    if (cur[1] == location) {
                        return res;
                    }
                    break;
                } else {
                    deque.addLast(cur);
                }
            }
        }
        return -1;
    }
}