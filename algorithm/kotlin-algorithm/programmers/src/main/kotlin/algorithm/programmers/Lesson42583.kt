package algorithm.programmers

import java.util.LinkedList

class Lesson42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bq = LinkedList<Int>().apply { repeat(bridge_length) { offer(0) } }
        val wq = LinkedList(truck_weights.toList())
        var sum = 0L
        var result = 0

        while (bq.isNotEmpty()) {
            sum -= bq.poll()
            ++result

            if (wq.isEmpty()) continue

            if (sum + wq.peek() <= weight) {
                val w = wq.poll()
                sum += w
                bq.offer(w)
            } else {
                bq.offer(0)
            }
        }

        return result
    }
}
