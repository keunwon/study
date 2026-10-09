import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        var q = new PriorityQueue<Integer>(scoville.length);
        for (var sc : scoville) {
            q.offer(sc);
        }

        var res = 0;
        while (q.size() > 1 && K > q.peek()) {
            var n1 = q.poll();
            var n2 = q.poll() * 2;
            
            ++res;
            q.offer(n1 + n2);
        }
        return (!q.isEmpty() && q.peek() >= K) ? res : -1;
    }
}