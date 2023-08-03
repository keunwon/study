package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridgeQueue = LinkedList(List(bridge_length) { 0 })
        val waitQueue = LinkedList(truck_weights.toList())
        var answer = 0

        while (bridgeQueue.isNotEmpty()) {
            bridgeQueue.poll()
            answer++

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
    val bridge_length = 100
    val weight = 100
    val truck_weights = intArrayOf(10)

    ALessons42583().solution(bridge_length, weight, truck_weights).also { println(it) }
}
