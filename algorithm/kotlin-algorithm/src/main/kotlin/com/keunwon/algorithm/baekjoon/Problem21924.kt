package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 도시 건설
 * Level: 골드-4
 **/
class Problem21924 {
    private lateinit var parent: IntArray

    fun solution(n: Int, arr: Array<IntArray>): Long {
        this.parent = IntArray(n + 1) { it }
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        var total = 0L

        arr.forEach { (a, b, c) ->
            total += c
            queue.offer(Node(a, b, c))
        }

        var shortestDistance = 0L
        var count = 0

        while (queue.isNotEmpty()) {
            if (count == n) break

            val cur = queue.poll()
            if (find(cur.src) != find(cur.dest)) {
                shortestDistance += cur.distance
                union(cur.src, cur.dest)
                count++
            }
        }
        return if (count != n - 1) -1 else total - shortestDistance
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n else find(parent[n])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }

    private data class Node(val src: Int, val dest: Int, val distance: Int)
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem21924().solution(n, arr).also(::println)
}
