package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (8)
 * Level: 실버-3
 **/
class Problem15657 {
    private val answer = mutableListOf<String>()

    fun solution(m: Int, numbers: IntArray): List<String> {
        numbers.sort()
        dfs(0, 0, IntArray(m), numbers)
        return answer
    }

    private fun dfs(
        depth: Int,
        startIndex: Int,
        picks: IntArray,
        numbers: IntArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in startIndex until numbers.size) {
            picks[depth] = numbers[i]
            dfs(depth + 1, i, picks, numbers)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()

    Problem15657().solution(m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
