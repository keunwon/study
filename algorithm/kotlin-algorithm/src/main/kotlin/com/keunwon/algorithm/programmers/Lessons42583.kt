package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 다리를 지나는 트럭
 * Level: 2
 **/
class Lessons42583 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val bridgeQueue = LinkedList<Int>().apply { repeat(bridge_length) { add(0) } }
        val weightQueue = LinkedList(truck_weights.toList())

        while (bridgeQueue.isNotEmpty()) {
            bridgeQueue.poll()
            answer++

            if (weightQueue.isEmpty()) continue

            if (bridgeQueue.sum() + weightQueue.peek() <= weight) {
                bridgeQueue.add(weightQueue.poll())
            } else {
                bridgeQueue.add(0)
            }
        }
        return answer
    }
}
