package algorithm.programmers

import java.util.*

class Lesson155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        val times = book_time.map { (s, e) -> Pair(s.toMinutes(), e.toMinutes() + 10) }
            .sortedWith(compareBy({ it.first }, { it.second }))
        val queue = PriorityQueue<Int>()

        for ((s, e) in times) {
            if (queue.isEmpty()) {
                queue.offer(e)
                continue
            }

            if (queue.peek() <= s) queue.poll()
            queue.offer(e)
        }
        return queue.size
    }

    private fun String.toMinutes(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }
}
