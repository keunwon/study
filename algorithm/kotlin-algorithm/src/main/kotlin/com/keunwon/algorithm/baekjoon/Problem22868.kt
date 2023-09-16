package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 산책(small)
 * Level: 골드-3
 **/
class Problem22868 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray

    fun solution(n: Int, edges: Array<IntArray>, s: Int, e: Int): Int {
        this.graph = Array(n + 1) { mutableListOf() }
        this.visited = BooleanArray(n + 1)

        edges.forEach { (a, b) ->
            graph[a].add(b)
            graph[b].add(a)
        }
        graph.forEach { it.sorted() }

        var answer = bfs(n, s, e)
        visited[s] = false
        answer += bfs(n, e, s)
        return answer
    }

    private fun bfs(n: Int, s: Int, e: Int): Int {
        val queue = LinkedList<Node>().apply {
            offer(Node(s, 0, "$s"))
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.index == e) {
                val route = cur.route.split(" ").map { it.toInt() }
                visited = BooleanArray(n + 1)

                for (num in route) visited[num] = true
                return cur.distance
            }

            for (next in graph[cur.index]) {
                if (visited[next]) continue

                visited[next] = true
                queue.offer(Node(next, cur.distance + 1, "${cur.route} $next"))
            }
        }
        return 0
    }

    private data class Node(val index: Int, val distance: Int, val route: String)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val edges = Array(m) {
            val st = StringTokenizer(br.readLine())
            IntArray(2) { st.nextToken().toInt() }
        }
        val (s, e) = br.readLine().split(" ").map { it.toInt() }

        Problem22868().solution(n, edges, s, e).also { bw.write("$it") }
        bw.flush()
    }
}
