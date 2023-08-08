package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Title: 우주신과의 교감
 * Level: 골드-3
 **/
class Problem1774 {
    private lateinit var parent: IntArray

    fun solution(spacePositions: Array<Pair<Int, Int>>, passages: Array<Pair<Int, Int>>): String {
        val n = spacePositions.size
        this.parent = IntArray(n + 1) { it }
        val queue = PriorityQueue<Edge>(compareBy { it.distance })

        passages.forEach { (x, y) -> union(x, y) }

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val (x1, y1) = spacePositions[i]
                val (x2, y2) = spacePositions[j]
                val distance = sqrt((x1 - x2).toDouble().pow(2) + (y1 - y2).toDouble().pow(2))

                queue.offer(Edge(i + 1, j + 1, distance))
            }
        }

        var answer = 0.0
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (find(cur.src) != find(cur.dest)) {
                union(cur.src, cur.dest)
                answer += cur.distance
            }
        }
        return "%.2f".format(answer)
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)

        if (find1 != find2) parent[find2] = find1
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n
        else {
            parent[n] = find(parent[n])
            parent[n]
        }
    }

    private data class Edge(val src: Int, val dest: Int, val distance: Double)
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val spacePositions = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }
    val passages = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }

    Problem1774().solution(spacePositions, passages).also(::println)
}
