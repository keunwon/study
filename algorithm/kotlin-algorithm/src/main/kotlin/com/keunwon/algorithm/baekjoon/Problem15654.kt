package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (5)
 * Level: 실버-3
 **/
class Problem15654 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int, m: Int, numbers: IntArray): List<String> {
        numbers.sort()
        dfs(0, numbers, IntArray(m), BooleanArray(n))

        return answer
    }

    private fun dfs(
        depth: Int,
        numbers: IntArray,
        picks: IntArray,
        visited: BooleanArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for ((i, num) in numbers.withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = num
            dfs(depth + 1, numbers, picks, visited)
            visited[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem15654().solution(n, m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
