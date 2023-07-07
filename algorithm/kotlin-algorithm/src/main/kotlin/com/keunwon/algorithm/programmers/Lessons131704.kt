package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 택배상자
 * Level: 2
 **/
class Lessons131704 {
    fun solution(order: IntArray): Int {
        val stack = Stack<Int>()
        var idx = 0

        for (i in 1..order.size) {
            if (i == order[idx]) idx++ else stack.push(i)

            while (stack.isNotEmpty() && stack.peek() == order[idx]) {
                stack.pop()
                idx++
            }
        }
        return idx
    }
}
