import java.util.PriorityQueue;

class Solution {
    public int[] solution(int k, int[] score) {
        var n = score.length;
        var res = new int[n];
        var q = new PriorityQueue<Integer>(k);

        for (var i = 0; i < n; i++) {
            var s = score[i];
            if (k > q.size()) {
                q.offer(s);
            } else if (k == q.size() && s > q.peek()) {
                q.poll();
                q.offer(s);
            }
            res[i] = q.peek();
        }
        return res;
    }
}