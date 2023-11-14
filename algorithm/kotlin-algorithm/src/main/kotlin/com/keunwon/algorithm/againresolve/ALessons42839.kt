package com.keunwon.algorithm.againresolve

import kotlin.math.sqrt

class ALessons42839 {
    private val set = mutableSetOf<Int>()

    fun solution(numbers: String): Int {
        dfs("", numbers, BooleanArray(numbers.length))
        return set.count(this::isPrime)
    }

    private fun dfs(str: String, numbers: String, visited: BooleanArray) {
        if (str.isNotBlank()) set.add(str.toInt())

        for ((i, num) in numbers.withIndex()) {
            if (visited[i]) continue
            visited[i] = true
            dfs("$str$num", numbers, visited)
            visited[i] = false
        }
    }

    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        return (2..sqrt(num.toFloat()).toInt()).none { num % it == 0 }
    }
}

fun main() {
    ALessons42839().solution("17").let(::println)
    ALessons42839().solution("011").let(::println)
}
