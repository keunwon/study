package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons142085 {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val queue = PriorityQueue<Int>()
        var hp = n

        for ((i, e) in enemy.withIndex()) {
            queue.offer(e)

            if (k < queue.size) hp -= queue.poll()
            if (hp < 0) return i
        }
        return enemy.size
    }
}

fun main() {
    ALessons142085().solution(
        7,
        3,
        intArrayOf(4, 2, 4, 5, 3, 3, 1)
    ).also(::println)

    ALessons142085().solution(
        2,
        4,
        intArrayOf(3, 3, 3, 3)
    ).also(::println)
}
