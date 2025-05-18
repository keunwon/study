package algorithm.programmers

class Lesson389480 {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val dp = Array(info.size + 1) { IntArray(m) { 1e9.toInt() } }
        dp[0][0] = 0

        for (i in 1..info.size) {
            val (a, b) = info[i - 1]
            for (j in 0 until m) {
                dp[i][j] = dp[i][j].coerceAtMost(dp[i - 1][j] + a)
                if (j + b < m) {
                    dp[i][j + b] = dp[i][j + b].coerceAtMost(dp[i - 1][j])
                }
            }
        }
        return dp.last().minOrNull()!!.let { if (it >= n) -1 else it }
    }
}
