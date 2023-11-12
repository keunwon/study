package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons76502 {
    fun solution(s: String): Int = s.indices.count {
        val text = s.substring(it) + s.take(it)
        check(text)
    }

    private fun check(text: String): Boolean {
        val brackets = mapOf('[' to ']', '(' to ')', '{' to '}')
        val stack = Stack<Char>()

        for (c in text) {
            if (brackets.containsKey(c)) {
                stack.push(c)
                continue
            }
            if (stack.isEmpty()) return false
            if (c == brackets[stack.peek()]) stack.pop()
        }
        return stack.isEmpty()
    }
}

fun main() {
    listOf("[](){}", "}]()[{", "[)(]", "}}}").forEach {
        ALessons76502().solution(it).also(::println)
    }
}
