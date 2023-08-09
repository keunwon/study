package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (7)
 * Level: 실버-3
 **/
class Problem15656 {
    private val answer = mutableListOf<String>()

    fun solution(m: Int, numbers: IntArray): List<String> {
        numbers.sort()
        dfs(0, numbers, IntArray(m))

        return answer
    }

    private fun dfs(
        depth: Int,
        numbers: IntArray,
        picks: IntArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for ((i, num) in numbers.withIndex()) {
            picks[depth] = num
            dfs(depth + 1, numbers, picks)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()


    Problem15656().solution(m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
