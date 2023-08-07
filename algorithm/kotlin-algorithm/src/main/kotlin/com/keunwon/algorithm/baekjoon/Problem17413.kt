package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 단어 뒤집기 2
 * Level: 실버-3
 **/
class Problem17413 {
    fun solution(text: String): String {
        val stack = Stack<Char>()
        val answer = StringBuilder()
        var flag = false

        for (c in text) {
            if (c == ' ' || c == '\n') {
                while (stack.isNotEmpty()) answer.append(stack.pop())
                answer.append(' ')
            } else if (c == '<') {
                while (stack.isNotEmpty()) answer.append(stack.pop())
                answer.append('<')
                flag = true
            } else if (c == '>') {
                answer.append('>')
                flag = false
            } else {
                if (flag) answer.append(c)
                else stack.push(c)
            }
        }

        while (stack.isNotEmpty()) answer.append(stack.pop())
        return answer.toString()
    }
}

fun main() {
    val text = readLine()!!
    Problem17413().solution(text).also(::println)
}
