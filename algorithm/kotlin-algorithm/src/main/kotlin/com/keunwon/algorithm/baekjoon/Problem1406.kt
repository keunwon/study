package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 에디터
 * Level: 실버-2
 **/
class Problem1406 {
    fun solution(init: String, commands: Array<Pair<Char, Char>>): String {
        val left = Stack<Char>().apply { init.forEach { push(it) } }
        val right = Stack<Char>()

        for ((option, char) in commands) {
            when (option) {
                'L' -> {
                    if (left.isEmpty()) continue
                    right.push(left.pop())
                }

                'D' -> {
                    if (right.isEmpty()) continue
                    left.push(right.pop())
                }

                'B' -> {
                    if (left.isEmpty()) continue
                    left.pop()
                }

                'P' -> left.push(char)
            }
        }

        while (left.isNotEmpty()) right.push(left.pop())

        val sb = StringBuilder()
        while (right.isNotEmpty()) sb.append(right.pop())
        return sb.toString()
    }
}

fun main() {
    val init = readLine()!!
    val n = readLine()!!.toInt()
    val command = Array(n) {
        readLine()!!.split(" ").let {
            val option = it[0][0]
            val char = if (it.size == 1) ' ' else it[1][0]
            option to char
        }
    }

    Problem1406().solution(init, command).also { println(it) }
}
