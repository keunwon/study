package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons42883 {
    fun solution(number: String, k: Int): String {
        val stack = Stack<Int>()
        var count = k

        for (i in number.indices) {
            val num = number[i] - '0'

            while (stack.isNotEmpty() && stack.peek() < num && count != 0) {
                stack.pop()
                count--
            }
            stack.push(num)
        }
        repeat(count) { stack.pop() }
        return stack.joinToString("")
    }
}

fun main() {
    arrayOf(
        "1924" to 2,
        "1231234" to 3,
        "4177252841" to 4,
        "4321" to 1,
    ).forEach { (number, k) ->
        ALessons42883().solution(number, k).also { println(it) }
    }
}
