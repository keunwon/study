package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (9)
 * Level: 실버-2
 **/
class Problem15663 {
    private val answer = mutableListOf<String>()

    fun solution(m: Int, numbers: IntArray): List<String> {
        numbers.sort()

        dfs(0, BooleanArray(numbers.size), IntArray(m), numbers)
        return answer.distinct()
    }

    private fun dfs(
        depth: Int,
        visited: BooleanArray,
        picks: IntArray,
        numbers: IntArray,
    ) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for ((i, num) in numbers.withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = num
            dfs(depth + 1, visited, picks, numbers)
            visited[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()

    Problem15663().solution(m, numbers).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
