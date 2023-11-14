package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons131704 {
    fun solution(order: IntArray): Int {
        var index = 0
        val stack = Stack<Int>()

        for (i in 1..order.size) {
            if (i == order[index]) ++index else stack.push(i)

            while (stack.isNotEmpty() && stack.peek() == order[index]) {
                ++index
                stack.pop()
            }
        }
        return index
    }
}

fun main() {
    ALessons131704().solution(
        intArrayOf(4, 3, 1, 2, 5)
    ).let(::println)

    ALessons131704().solution(
        intArrayOf(5, 4, 3, 2, 1)
    ).let(::println)
}
