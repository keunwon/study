package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 바이러스
 * Level: 실버-3
 **/
class Problem2606 {
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(n: Int, arr: Array<Pair<Int, Int>>): Int {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            arr.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        val visited = BooleanArray(n + 1).apply { this[1] = true }
        val queue = LinkedList<Int>().apply { offer(1) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                if (visited[next]) continue

                visited[next] = true
                queue.offer(next)
            }
        }
        return visited.count { it } - 1
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        val arr = Array(m) {
            br.readLine()
                .split(" ")
                .let { it[0].toInt() to it[1].toInt() }
        }

        Problem2606().solution(n, arr).also { bw.write("$it") }
        bw.flush()
    }
}
