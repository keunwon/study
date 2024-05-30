package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

class Lesson42839 {
    private val answer = mutableSetOf<Int>()

    fun solution(numbers: String): Int {
        for (i in numbers.indices) {
            permutation(i + 1, "", numbers, BooleanArray(numbers.length))
        }
        return answer.count {
            println("$it, ${isPrime(it)}")
            isPrime(it)
        }
    }

    private fun permutation(size: Int, cur: String, numbers: String, visited: BooleanArray) {
        if (size == 0) {
            answer.add(cur.toInt())
            return
        }

        for ((i, num) in numbers.withIndex()) {
            if (!visited[i]) {
                visited[i] = true
                permutation(size - 1, "$cur$num", numbers, visited)
                visited[i] = false
            }
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 }
    }
}
