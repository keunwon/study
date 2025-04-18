package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 뱀과 사다리 게임
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/16928">뱀과 사다리 게임 (골드-5)</a>
 **/
class Problem16928 {
    fun solution(infos: Array<IntArray>): Int {
        val board = IntArray(101) { it }.apply { infos.forEach { (a, b) -> this[a] = b } }
        val q = LinkedList<Int>().apply { offer(1) }
        val dp = IntArray(101)

        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur >= 100) break

            for (i in 1..6) {
                val next = cur + i
                if (next > 100 || dp[next] != 0) continue

                if (dp[board[next]] == 0) {
                    q.offer(board[next])
                    dp[board[next]] = dp[cur] + 1
                }
            }
        }
        return dp[100]
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val infos = Array(n + m) { IntArray(2) }

    repeat(n + m) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        infos[it][0] = arr[0]
        infos[it][1] = arr[1]
    }

    Problem16928().solution(infos).also { println(it) }
}
