package com.keunwon.algorithm.baekjoon

/**
 * Title: N 과 M (12)
 * Level: 실버-2
 **/
class Problem15666 {
    private val answer = mutableListOf<String>()

    fun solution(m: Int, numbers: IntArray): List<String> {
        numbers.sort()
        dfs(0, 0, numbers, IntArray(m))

        return answer.distinct()
    }

    private fun dfs(
        depth: Int,
        startIndex: Int,
        numbers: IntArray,
        picks: IntArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in startIndex until numbers.size) {
            picks[depth] = numbers[i]
            dfs(depth + 1, i, numbers, picks)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()

    Problem15666().solution(m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
