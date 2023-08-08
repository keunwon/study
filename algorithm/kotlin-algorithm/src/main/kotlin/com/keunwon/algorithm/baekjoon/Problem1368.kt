package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 물대기
 * Level: 골드-2
 **/
class Problem1368 {
    private lateinit var parents: IntArray

    fun solution(n: Int, costs: IntArray, edges: Array<IntArray>): Int {
        this.parents = IntArray(n + 1) { it }
        val queue = PriorityQueue<Edge>(compareBy { it.distance })

        for ((i, arr) in edges.withIndex()) {
            for ((j, num) in arr.withIndex()) {
                if (i == j) queue.offer(Edge(0, i + 1, costs[i]))
                else queue.offer(Edge(i + 1, j + 1, num))
            }
        }

        var answer = 0
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (find(cur.src) != find(cur.dest)) {
                answer += cur.distance
                union(cur.src, cur.dest)
            }
        }
        return answer
    }

    private fun find(n: Int): Int {
        return if (parents[n] == n) n
        else {
            parents[n] = find(parents[n])
            parents[n]
        }
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        if (find1 != find2) parents[find2] = find1
    }

    private data class Edge(
        val src: Int,
        val dest: Int,
        val distance: Int,
    )
}

fun main() {
    val n = readLine()!!.toInt()
    val costs = IntArray(n) { readLine()!!.toInt() }
    val edges = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem1368().solution(n, costs, edges).also(::println)
}
