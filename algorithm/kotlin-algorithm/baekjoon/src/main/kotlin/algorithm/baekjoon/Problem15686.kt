package algorithm.baekjoon

import kotlin.math.abs

/**
 * <p>
 * 이름: 치킨 배달
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/15686">치킨 배달 (골드-5)</a>
 **/
class Problem15686 {
    private val homes = mutableListOf<Pair<Int, Int>>()
    private val chickens = mutableListOf<Pair<Int, Int>>()

    fun solution(m: Int, board: Array<IntArray>): Int {
        for (i in board.indices) {
            for ((j, n) in board[i].withIndex()) {
                when (n) {
                    1 -> homes.add(i to j)
                    2 -> chickens.add(i to j)
                }
            }
        }
        return (1..m).minOf { dfs(it, 0, mutableListOf()) }
    }

    private fun dfs(
        size: Int,
        index: Int,
        picks: MutableList<Pair<Int, Int>>,
    ): Int {
        if (size == picks.size) {
            val dp = IntArray(homes.size) { 1e9.toInt() }
            for ((i, home) in homes.withIndex()) {
                for (pick in picks) {
                    val diff = abs(home.first - pick.first) + abs(home.second - pick.second)
                    dp[i] = dp[i].coerceAtMost(diff)
                }
            }
            return dp.sum()
        }

        var result = 1e9.toInt()
        for (i in index until chickens.size) {
            picks.add(chickens[i])
            result = result.coerceAtMost(dfs(size, i + 1, picks))
            picks.removeLast()
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    Problem15686().solution(m, board).also(::println)
}
