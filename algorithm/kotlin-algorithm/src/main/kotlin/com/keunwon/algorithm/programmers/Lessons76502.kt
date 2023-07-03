package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 괄호 회전하기
 * Level: 2
 **/
class Lessons76502 {
    fun solution(s: String): Int {
        var answer = 0
        for (i in s.indices) {
            val str = s.substring(i) + s.take(i)
            if (check(str)) answer++
        }
        return answer
    }

    private fun check(str: String): Boolean {
        val map = mapOf('[' to ']', '{' to '}', '(' to ')')
        val stack = Stack<Char>()

        for (c in str) {
            if (map.contains(c)) {
                stack.push(c)
                continue
            }
            if (stack.isEmpty()) return false
            if (map.getValue(stack.peek()) == c) stack.pop()
        }
        return stack.isEmpty()
    }
}
