package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: DFS와 BFS
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1260">DFS와 BFS (실버-2)</a>
 **/
class Problem1260 {
    private lateinit var grpah: Array<BooleanArray>
    private val result = List(2) { mutableListOf<Int>() }

    fun solution(n: Int, v: Int, edges: Array<Pair<Int, Int>>): List<List<Int>> {
        this.grpah = Array(n + 1) { BooleanArray(n + 1) }.apply {
            edges.forEach { (a, b) ->
                this[a][b] = true
                this[b][a] = true
            }
        }

        dfs(v, BooleanArray(n + 1))
        bfs(v, n)

        return result
    }

    private fun bfs(v: Int, n: Int) {
        val q = LinkedList<Int>().apply { offer(v) }
        val visited = BooleanArray(n + 1).apply { this[v] = true }

        while (q.isNotEmpty()) {
            val cur = q.poll()
            result[1].add(cur)

            for (i in 1..n) {
                if (grpah[cur][i] && !visited[i]) {
                    visited[i] = true
                    q.offer(i)
                }
            }
        }
    }

    private fun dfs(v: Int, visited: BooleanArray) {
        visited[v] = true
        result[0].add(v)

        for (i in 1 until visited.size) {
            if (grpah[v][i] && !visited[i]) dfs(i, visited)
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        arr[0] to arr[1]
    }

    Problem1260().solution(n, v, edges).forEach { println(it.joinToString(" ")) }
}
