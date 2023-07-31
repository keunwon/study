package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons142085 {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val queue = PriorityQueue<Int>()
        var life = n

        for ((i, e) in enemy.withIndex()) {
            queue.offer(e)
            if (queue.size <= k) continue

            life -= queue.poll()
            if (life < 0) return i
        }
        return enemy.size
    }
}

fun main() {
    val n = 7
    val k = 3
    val enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1)

    ALessons142085().solution(n, k, enemy).also { println(it) }
}
