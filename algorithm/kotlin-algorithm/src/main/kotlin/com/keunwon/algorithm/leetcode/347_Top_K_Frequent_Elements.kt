package com.keunwon.algorithm.leetcode

import java.util.*

class `347_Top_K_Frequent_Elements` {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val queue = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })
        val map = nums.asSequence().groupingBy { it }.eachCount()

        for ((key, value) in map) {
            queue.offer(key to value)
        }
        return IntArray(k) { queue.poll().first }
    }
}

fun main() {
    `347_Top_K_Frequent_Elements`()
        .topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2)
        .also { println(it.joinToString(", ")) } // [1, 2]

    `347_Top_K_Frequent_Elements`()
        .topKFrequent(intArrayOf(1), 1)
        .also { println(it.joinToString(", ")) } // [1]
}
