package com.keunwon.algorithm.baekjoon

class Problem15652 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int, m: Int): List<String> {
        dfs(0, 1, IntArray(m), n)
        return answer
    }

    private fun dfs(depth: Int, startIndex: Int, picks: IntArray, n: Int) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in startIndex..n) {
            picks[depth] = i
            dfs(depth + 1, i, picks, n)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (n, m) = readLine().split(" ").map { it.toInt() }

    Problem15652().solution(n, m).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
