package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridgeQueue = LinkedList(List(bridge_length) { 0 })
        val waitQueue = LinkedList(truck_weights.toList())
        var answer = 0

        while (bridgeQueue.isNotEmpty()) {
            bridgeQueue.poll()
            ++answer

            if (waitQueue.isEmpty()) continue

            if (bridgeQueue.sum() + waitQueue.peek() <= weight) {
                bridgeQueue.offer(waitQueue.poll())
            } else {
                bridgeQueue.offer(0)
            }
        }
        return answer
    }
}

fun main() {
    ALessons42583().solution(
        2,
        10,
        intArrayOf(7, 4, 5, 6)
    ).also(::println)

    ALessons42583().solution(
        100,
        100,
        intArrayOf(10)
    ).also(::println)

    ALessons42583().solution(
        100,
        100,
        intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
    ).also(::println)
}
