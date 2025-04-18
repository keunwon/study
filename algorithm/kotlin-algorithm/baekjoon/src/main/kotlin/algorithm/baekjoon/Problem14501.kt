package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * <p>
 * 이름: 퇴사
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14501">퇴사 (실버-3)</a>
 **/
class Problem14501 {
    fun solution(schedules: Array<Pair<Int, Int>>): Int {
        val n = schedules.size
        val dp = IntArray(n + 1)

        for (i in schedules.indices) {
            val (t, p) = schedules[i]

            if (i + t <= n) {
                dp[i + t] = dp[i + t].coerceAtLeast(dp[i] + p)
            }
            dp[i + 1] = dp[i + 1].coerceAtLeast(dp[i])
        }
        return dp[n]
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val schedules = Array(n) {
        val st = StringTokenizer(readLine())
        st.nextToken().toInt() to st.nextToken().toInt()
    }

    println(Problem14501().solution(schedules))
}
