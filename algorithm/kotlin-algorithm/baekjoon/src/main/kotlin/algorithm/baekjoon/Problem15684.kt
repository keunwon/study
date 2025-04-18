package algorithm.baekjoon

/**
 * <p>
 * 이름: 사다리 조작
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/15684">사다리 조작 (골드-3)</a>
 **/
class Problem15684 {
    private lateinit var graph: Array<BooleanArray>
    private var n = 0
    private var h = 0

    fun solution(n: Int, h: Int, lines: Array<Pair<Int, Int>>): Int {
        this.graph = Array(h + 1) { BooleanArray(n + 1) }.apply {
            lines.forEach { (u, v) -> this[u][v] = true }
        }
        this.n = n
        this.h = h

        for (i in 0..3) {
            val valid = dfs(1, 0, i)
            if (valid) return i
        }
        return -1
    }

    private fun dfs(depth: Int, change: Int, max: Int): Boolean {
        if (change == max) {
            for (i in 1..n) {
                var r = 1
                var c = i

                while (r <= h) {
                    if (graph[r][c]) ++c
                    else if (graph[r][c - 1]) --c
                    ++r
                }
                if (i != c) return false
            }
            return true
        }

        for (r in depth..h) {
            for (c in 1 until n) {
                if (!graph[r][c] && !graph[r][c - 1] && !graph[r][c + 1]) {
                    graph[r][c] = true
                    if (dfs(r, change + 1, max)) return true
                    graph[r][c] = false
                }
            }
        }
        return false
    }
}

fun main() {
    val (n, m, h) = readln().split(" ").map { it.toInt() }
    val lines = Array(m) {
        val arr = readln().split(" ").map { it.toInt() }
        arr[0] to arr[1]
    }

    Problem15684().solution(n, h, lines).also(::println)
}
