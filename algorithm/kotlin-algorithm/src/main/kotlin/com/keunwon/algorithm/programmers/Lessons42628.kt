package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 이중우선순위큐
 * Level: 3
 **/
class Lessons42628 {
    fun solution(operations: Array<String>): IntArray {
        val minQueue = PriorityQueue<Int>()
        val maxQueue = PriorityQueue<Int>(compareBy { -it })

        for (operation in operations) {
            val (option, num) = operation.split(" ").let { it[0] to it[1].toInt() }

            if (option == "I") {
                minQueue.add(num)
                maxQueue.add(num)
            } else if (option == "D") {
                if (minQueue.isEmpty()) continue

                if (num == 1) minQueue.remove(maxQueue.poll())
                else if (num == -1) maxQueue.remove(minQueue.poll())
            }
        }
        return if (minQueue.isEmpty()) intArrayOf(0, 0)
        else intArrayOf(maxQueue.peek(), minQueue.peek())
    }
}
