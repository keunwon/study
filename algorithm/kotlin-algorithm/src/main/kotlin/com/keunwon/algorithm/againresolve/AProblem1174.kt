package com.keunwon.algorithm.againresolve

class AProblem1174 {
    private var n = 0
    private val numbers = intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    private val set = mutableSetOf<Long>()

    fun solution(n: Int): Long {
        this.n = n

        dfs(0, 0L)
        return runCatching { set.sorted()[n - 1] }.getOrDefault(-1)
    }

    private fun dfs(index: Int, sum: Long) {
        if (!set.contains(sum)) set.add(sum)

        if (index == numbers.size) return

        dfs(index + 1, sum * 10 + numbers[index])
        dfs(index + 1, sum)
    }
}

fun main() {
    val n = readLine()!!.toInt()
    AProblem1174().solution(n).also(::println)
}
