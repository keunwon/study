package com.keunwon.algorithm.baekjoon

/**
 * Title: N과 M (1)
 * Level: 실버-3
 **/
class Problem15649 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int, m: Int): List<String> {
        dfs(0, n, IntArray(m), BooleanArray(10))
        return answer.sorted()
    }

    private fun dfs(depth: Int, n: Int, picks: IntArray, visited: BooleanArray) {
        if (depth == picks.size) {
            answer.add(picks.joinToString(" "))
            return
        }

        for (i in 1..n) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = i
            dfs(depth + 1, n, picks, visited)
            visited[i] = false
        }
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    Problem15649().solution(n, m).forEach(::println)
}
