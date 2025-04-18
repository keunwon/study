package algorithm.programmers

import java.util.LinkedList

class Lesson118667 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val q1 = LinkedList(queue1.map { it.toLong() })
        val q2 = LinkedList(queue2.map { it.toLong() })
        var sum1 = q1.sum()
        var sum2 = q2.sum()
        var count = 0

        if ((sum1 + sum2) % 2 != 0L) return -1

        while (sum1 != sum2) {
            ++count

            if (sum1 < sum2) {
                val tmp = q2.poll()
                q1.offer(tmp)
                sum1 += tmp
                sum2 -= tmp
            } else {
                val tmp = q1.poll()
                q2.offer(tmp)
                sum1 -= tmp
                sum2 += tmp
            }

            if (count > (q1.size + q2.size) * 2) return -1
        }
        return count
    }
}
