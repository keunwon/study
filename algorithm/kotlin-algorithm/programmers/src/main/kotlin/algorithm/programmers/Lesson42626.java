package algorithm.programmers;

import java.util.PriorityQueue;

public class Lesson42626 {
    public int solution(int[] scoville, int K) {
        var queue = new PriorityQueue<Integer>();

        for (int sc : scoville) {
            queue.offer(sc);
        }

        if (queue.peek() >= K) return 0;

        var step = 0;
        while (queue.size() > 1) {
            var sum = queue.poll() + queue.poll() * 2;
            queue.offer(sum);
            ++step;

            if (queue.peek() >= K) return step;
        }
        return -1;
    }
}
