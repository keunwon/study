package algorithm.programmers

import java.util.*

class Lesson42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridgeQueue = LinkedList<Long>().apply { repeat(bridge_length) { offer(0L) } }
        val waiteQueue = LinkedList(truck_weights.map { it.toLong() })
        var answer = 0

        while (bridgeQueue.isNotEmpty()) {
            bridgeQueue.poll()
            ++answer

            if (waiteQueue.isEmpty()) continue

            if (bridgeQueue.sum() + waiteQueue.peek() <= weight) {
                bridgeQueue.offer(waiteQueue.poll())
            } else {
                bridgeQueue.offer(0)
            }
        }
        return answer
    }
}
