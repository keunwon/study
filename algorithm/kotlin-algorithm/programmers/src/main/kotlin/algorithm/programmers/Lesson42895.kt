package algorithm.programmers

import kotlin.math.min

class Lesson42895 {
    private var answer = Int.MAX_VALUE

    fun solution(N: Int, number: Int): Int {
        dfs(0, 0, N, number)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(depth: Int, sum: Int, n: Int, number: Int) {
        if (depth > 8) return

        if (sum == number) {
            answer = min(answer, depth)
            return
        }

        var tmp = 0
        for (i in 1..8) {
            tmp = tmp * 10 + n
            dfs(depth + i, sum + tmp, n, number)
            dfs(depth + i, sum - tmp, n, number)
            dfs(depth + i, sum * tmp, n, number)
            dfs(depth + i, sum / tmp, n, number)
        }
    }
}
