package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons42628 {
    fun solution(operations: Array<String>): IntArray {
        val minQueue = PriorityQueue<Int>()
        val maxQueue = PriorityQueue<Int>(compareByDescending { it })
        val isQueueEmpty = { minQueue.isEmpty() || maxQueue.isEmpty() }

        for (op in operations) {
            val (command, num) = op.split(" ").let { Pair(it[0], it[1].toInt()) }

            if ("I" == command) {
                minQueue.offer(num)
                maxQueue.offer(num)
            } else if ("D" == command) {
                if (isQueueEmpty()) continue
                if (num == 1) minQueue.remove(maxQueue.poll())
                else if (num == -1) maxQueue.remove(minQueue.poll())
            }
        }
        return if (isQueueEmpty()) intArrayOf(0, 0)
        else intArrayOf(maxQueue.peek(), minQueue.peek())
    }
}

fun main() {
    ALessons42628().solution(
        arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")
    ).also { println(it.contentToString()) }

    ALessons42628().solution(
        arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")
    ).also { println(it.contentToString()) }
}
