package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (2)
 * Level: 실버-3
 **/
class Problem15650 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int, m: Int): List<String> {
        dfs(0, 1, n, IntArray(m), BooleanArray(n + 1))
        return answer.sorted()
    }

    private fun dfs(depth: Int, startIndex: Int, n: Int, picks: IntArray, visited: BooleanArray) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in startIndex..n) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = i
            dfs(depth + 1, i + 1, n, picks, visited)
            visited[i] = false
        }
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    Problem15650().solution(n, m).forEach(::println)
}
