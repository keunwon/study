import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        var q = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for (var i = 0; i < enemy.length; i++) {
            var e = enemy[i];
            n -= e;
            q.offer(e);

            if (n < 0) {
                if (k == 0) return i;
                n += q.poll();
                --k;
            }
        }
        return enemy.length;
    }
}