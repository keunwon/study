package com.keunwon.algorithm.baekjoon

/**
 * Title: 줄어드는 수
 * Level: 골드-5
 **/
class Problem1174 {
    private val numbers = intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    private val list = mutableListOf<Long>()

    fun solution(n: Int): Long {
        dfs(0L, 0)
        list.sort()

        return runCatching { list[n - 1] }.getOrDefault(-1)
    }

    private fun dfs(num: Long, index: Int) {
        if (!list.contains(num)) list.add(num)

        if (index == numbers.size) return

        dfs(num * 10 + numbers[index], index + 1)
        dfs(num, index + 1)
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem1174().solution(n).also(::println)
}
