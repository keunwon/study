package com.keunwon.algorithm.programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class Lesson12927 {
    public long solution(int n, int[] works) {
        var queue = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int work : works) {
            queue.offer(work);
        }

        for (int i = 0; i < n; i++) {
            if (queue.isEmpty() || queue.peek() == 0) break;

            var cur = queue.poll();
            queue.offer(cur - 1);
        }

        var answer = 0L;
        while (!queue.isEmpty()) {
            var cur = (long) queue.poll();
            answer += cur * cur;
        }
        return answer;
    }
}
