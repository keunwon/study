package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

/**
 * Title: 소수 찾기
 * Level: 2
 **/
class Lessons42839 {
    private val set = mutableSetOf<Int>()

    fun solution(numbers: String): Int {
        dfs("", BooleanArray(numbers.length), numbers)
        return set.count { isPrime(it) }
    }

    private fun dfs(str: String, visited: BooleanArray, numbers: String) {
        if (str.isNotBlank()) set.add(str.toInt())

        for ((i, num) in numbers.withIndex()) {
            if (visited[i]) continue
            visited[i] = true
            dfs(str + "$num", visited, numbers)
            visited[i] = false
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 }
    }
}
