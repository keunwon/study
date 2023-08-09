package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (11)
 * Level: 실버-2
 **/
class Problem15665 {
    private val answer = mutableListOf<String>()

    fun solution(m: Int, numbers: IntArray): List<String> {
        numbers.sort()
        dfs(0, IntArray(m), numbers)

        return answer.distinct()
    }

    private fun dfs(
        depth: Int,
        picks: IntArray,
        numbers: IntArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for ((i, num) in numbers.withIndex()) {
            picks[depth] = num
            dfs(depth + 1, picks, numbers)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()

    Problem15665().solution(m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
