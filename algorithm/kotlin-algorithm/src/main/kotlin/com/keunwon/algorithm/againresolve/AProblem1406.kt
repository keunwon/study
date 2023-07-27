package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem1406 {
    fun solution(word: String, commands: Array<Pair<Char, Char>>): String {
        val left = Stack<Char>().apply { word.forEach { push(it) } }
        val right = Stack<Char>()

        for ((option, str) in commands) {
            when (option) {
                'L' -> if (left.isNotEmpty()) right.push(left.pop())
                'D' -> if (right.isNotEmpty()) left.push(right.pop())
                'B' -> if (left.isNotEmpty()) left.pop()
                'P' -> left.push(str)
            }
        }

        while (left.isNotEmpty()) right.push(left.pop())
        return right.joinToString("").reversed()
    }
}

fun main() {
    val word = readLine()!!
    val n = readLine()!!.toInt()
    val commands = Array(n) {
        readLine()!!
            .split(" ")
            .let { it[0][0] to if (it.size == 2) it[1][0] else ' ' }
    }

    AProblem1406().solution(word, commands).also { println(it) }
}
