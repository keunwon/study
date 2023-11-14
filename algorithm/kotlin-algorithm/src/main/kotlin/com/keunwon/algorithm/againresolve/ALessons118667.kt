package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons118667 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val q1 = LinkedList(queue1.toList())
        val q2 = LinkedList(queue2.toList())
        var sum1 = q1.sumOf { it.toLong() }
        var sum2 = q2.sumOf { it.toLong() }
        var count = 0

        if ((sum1 + sum2) % 2 != 0L) return -1

        while (sum1 != sum2) {
            if (sum1 < sum2) {
                val cur = q2.poll()
                sum1 += cur
                sum2 -= cur
                q1.offer(cur)
            } else {
                val cur = q1.poll()
                sum1 -= cur
                sum2 += cur
                q2.offer(cur)
            }
            ++count
            if (count > (q1.size + q2.size) * 2) return -1
        }
        return count
    }
}

fun main() {
    ALessons118667().solution(
        intArrayOf(3, 2, 7, 2),
        intArrayOf(4, 6, 5, 1)
    ).also(::println)

    ALessons118667().solution(
        intArrayOf(1, 2, 1, 2),
        intArrayOf(1, 10, 1, 2)
    ).also(::println)

    ALessons118667().solution(
        intArrayOf(1, 1),
        intArrayOf(1, 5)
    ).also(::println)
}
