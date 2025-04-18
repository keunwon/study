package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 숨바꼭질
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1697">숨바꼭질 (실버-1)</a>
 **/
class Problem1697 {
    fun solution(n: Int, k: Int): Int {
        val q = LinkedList<Int>().apply { offer(n) }
        val dp = IntArray(100_001) { 1e9.toInt() }.apply { this[n] = 0 }

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur == k) break

            for (next in intArrayOf(cur - 1, cur + 1, cur * 2)) {
                if (next in dp.indices && dp[next] > dp[cur] + 1) {
                    dp[next] = dp[cur] + 1
                    q.offer(next)
                }
            }
        }
        return dp[k]
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    Problem1697().solution(n, k).also { println(it) }
}
