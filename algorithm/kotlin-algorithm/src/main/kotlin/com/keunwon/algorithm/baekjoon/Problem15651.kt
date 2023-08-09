package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (3)
 * Level: 실버-3
 **/
class Problem15651 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int, m: Int): List<String> {
        dfs(0, n, IntArray(m))
        return answer
    }

    private fun dfs(depth: Int, n: Int, picks: IntArray) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in 1..n) {
            picks[depth] = i
            dfs(depth + 1, n, picks)
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    Problem15651().solution(n, m).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
}
