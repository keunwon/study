package com.keunwon.algorithm.baekjoon


/**
 * Title: 부분수열의 합
 * Level: 실버-2
 **/
class Problem1182 {
    private var answer = 0

    fun solution(s: Int, numbers: IntArray): Int {
        dfs(0, 0, numbers, s)
        return if (s === 0) --answer else answer
    }

    private fun dfs(index: Int, sum: Int, numbers: IntArray, s: Int) {
        if (index == numbers.size) {
            if (sum == s) answer++
            return
        }
        dfs(index + 1, sum, numbers, s)
        dfs(index + 1, sum + numbers[index], numbers, s)
    }
}

fun main() {
    val (n, s) = readLine()!!.split(" ").map { it.toInt() }
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1182().solution(s, numbers).also(::println)
}
