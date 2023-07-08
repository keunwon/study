package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 두 큐 합 같게 만들기
 * Level: 2
 **/
class Lessons118667 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val q1 = LinkedList<Long>().apply { queue1.forEach { add(it.toLong()) } }
        val q2 = LinkedList<Long>().apply { queue2.forEach { add(it.toLong()) } }
        var sum1 = q1.sum()
        var sum2 = q2.sum()
        var count = 0

        if ((sum1 + sum2) / 2 == 0L) return -1

        while (sum1 != sum2) {
            count++

            if (sum1 < sum2) {
                val tmp = q2.poll()
                sum1 += tmp
                sum2 -= tmp
                q1.add(tmp)
            } else {
                val tmp = q1.poll()
                sum1 -= tmp
                sum2 += tmp
                q2.add(tmp)
            }
            if (count > (q1.size + q2.size) * 2) return -1
        }
        return count
    }
}
