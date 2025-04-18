package algorithm.programmers

class Lesson12945 {
    fun solution(n: Int): Int {
        val dp = IntArray(n + 4).apply {
            this[2] = 1
            this[3] = 2
        }

        for (i in 4..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567
        }
        return dp[n]
    }
}
