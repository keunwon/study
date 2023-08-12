package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

/**
 * Title: N-Queen
 * Level: 골드-4
 **/
class Problem9663 {
    private lateinit var arr: IntArray
    private var n = 0
    private var answer = 0

    fun solution(n: Int): Int {
        this.n = n
        this.arr = IntArray(n)

        dfs(0)
        return answer
    }

    private fun dfs(depth: Int) {
        if (n == depth) {
            answer++
            return
        }

        for (i in 0 until n) {
            arr[depth] = i

            if (check(depth)) dfs(depth + 1)
        }
    }

    private fun check(col: Int): Boolean {
        for (i in 0 until col) {
            if (arr[col] == arr[i]) return false

            if (abs(col - i) == abs(arr[col] - arr[i])) return false
        }
        return true
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem9663().solution(n).also(::println)
}
