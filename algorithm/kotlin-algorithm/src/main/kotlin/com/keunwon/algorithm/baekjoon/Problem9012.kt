package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 괄호
 * Level: 실버-4
 **/
class Problem9012 {
    fun solution(word: String): String {
        val stack = Stack<Char>()

        for (c in word) {
            if (c == '(') stack.push(c)
            else if (c == ')') {
                if (stack.isNotEmpty()) stack.pop()
                else return "NO"
            }
        }
        return if (stack.isEmpty()) "YES" else "NO"
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val word = readLine()!!
        Problem9012().solution(word).also { println(it) }
    }
}
