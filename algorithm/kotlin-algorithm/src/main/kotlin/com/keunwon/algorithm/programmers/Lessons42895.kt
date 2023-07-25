package com.keunwon.algorithm.programmers

/**
 * Title: N으로 표현
 * Level: 3
 **/
class Lessons42895 {
    private var answer = Int.MAX_VALUE

    fun solution(N: Int, number: Int): Int {
        dfs(0, 0, N, number)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(count: Int, calcNum: Int, n: Int, number: Int) {
        if (count > 8) return
        if (calcNum == number) {
            answer = answer.coerceAtMost(count)
            return
        }

        var tmp = 0
        for (i in 1..8) {
            tmp = tmp * 10 + n
            dfs(count + i, calcNum + tmp, n, number)
            dfs(count + i, calcNum - tmp, n, number)
            dfs(count + i, calcNum * tmp, n, number)
            dfs(count + i, calcNum / tmp, n, number)
        }
    }
}
