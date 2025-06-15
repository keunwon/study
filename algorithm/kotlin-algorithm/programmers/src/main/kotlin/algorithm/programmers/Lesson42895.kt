package algorithm.programmers

class Lesson42895 {
    fun solution(N: Int, number: Int): Int {
        return dfs(N, number, 0, 0).takeIf { it != 1e9.toInt() } ?: -1
    }

    private fun dfs(n: Int, number: Int, cur: Int, depth: Int): Int {
        if (depth > 8) return 1e9.toInt()
        if (number == cur) return depth

        var min = 1e9.toInt()
        var tmp = 0
        for (i in 1..8) {
            tmp = tmp * 10 + n
            min = min
                .coerceAtMost(dfs(n, number, cur + tmp, depth + i))
                .coerceAtMost(dfs(n, number, cur - tmp, depth + i))
                .coerceAtMost(dfs(n, number, cur * tmp, depth + i))
                .coerceAtMost(dfs(n, number, cur / tmp, depth + i))
        }

        return min
    }
}
