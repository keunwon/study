package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 숨바꼭질 3
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/13549">숨바꼭질 3 (골드-5)</a>
 **/
class Problem13549 {
    fun solution(n: Int, k: Int): Int {
        val q = LinkedList<Int>().apply { offer(n) }
        val dp = IntArray(100_001) { 1e9.toInt() }.apply { this[n] = 0 }

        while (q.isNotEmpty()) {
            val cur = q.poll()
            val nexties = intArrayOf(cur - 1, cur + 1, cur * 2)

            if (cur == k) break

            for ((i, next) in nexties.withIndex()) {
                val second = dp[cur] + if (i == 2) 0 else 1
                if (next in dp.indices && dp[next] > second) {
                    dp[next] = second
                    q.offer(next)
                }
            }
        }
        return dp[k]
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    Problem13549().solution(n, k).also { println(it) }
}
