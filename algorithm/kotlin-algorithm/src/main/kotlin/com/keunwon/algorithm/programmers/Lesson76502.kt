package com.keunwon.algorithm.programmers

import java.util.*

class Lesson76502 {
    fun solution(s: String): Int {
        var answer = 0
        for (i in s.indices) {
            val str = s.substring(i) + s.take(i)
            if (check(str)) ++answer
        }
        return answer
    }

    private fun check(s: String): Boolean {
        val stack = Stack<Char>()
        val signs = mapOf(
            '[' to ']',
            '{' to '}',
            '(' to ')',
        )

        for (c in s) {
            if (signs.containsKey(c)) {
                stack.push(c)
                continue
            }

            if (stack.isEmpty()) return false
            if (c == signs.getValue(stack.peek())) stack.pop()
        }
        return stack.isEmpty()
    }
}
