package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 효율적인 해킹
 * Level: 실버-1
 **/
class Problem1325 {
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(n: Int, arr: Array<Pair<Int, Int>>): List<Int> {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            arr.forEach { (a, b) -> this[b].add(a) }
        }
        var max = 0
        var answer = IntArray(n + 1)

        for (i in 1..n) {
            val count = bfs(i, n)
            max = max.coerceAtLeast(count)
            answer[i] = count
        }
        return (1..n).filter { answer[it] == max }
    }

    private fun bfs(startIndex: Int, n: Int): Int {
        val queue = LinkedList<Int>()
        val visited = BooleanArray(n + 1)
        var count = 0

        queue.offer(startIndex)
        visited[startIndex] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            count++

            for (next in graph[cur]) {
                if (visited[next]) continue

                visited[next] = true
                queue.offer(next)
            }
        }
        return count
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val arr = Array(m) {
            val st = StringTokenizer(br.readLine())
            Pair(st.nextToken().toInt(), st.nextToken().toInt())
        }

        Problem1325().solution(n, arr).also { bw.write("${it.joinToString(" ")}") }
        bw.flush()
    }
}
