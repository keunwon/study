package com.keunwon.algorithm.againresolve

class ALessons42895 {
    private var answer = Int.MAX_VALUE

    fun solution(n: Int, number: Int): Int {
        dfs(0, 0, n, number)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(count: Int, total: Int, n: Int, number: Int) {
        if (count > 8) return
        if (total == number) {
            answer = answer.coerceAtMost(count)
            return
        }

        var tmp = 0
        for (i in 1..8) {
            tmp = tmp * 10 + n
            dfs(count + i, total + tmp, n, number)
            dfs(count + i, total - tmp, n, number)
            dfs(count + i, total * tmp, n, number)
            dfs(count + i, total / tmp, n, number)
        }
    }
}

fun main() {
    ALessons42895().solution(5, 12).also(::println)
    ALessons42895().solution(2, 11).also(::println)
}
