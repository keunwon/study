package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 행성 연결
 * Level: 골드-4
 **/
class Problem16398 {
    private lateinit var parent: IntArray

    fun solution(n: Int, arr: Array<IntArray>): Long {
        this.parent = IntArray(n + 1) { it }
        val queue = PriorityQueue<Edge>(compareBy { it.distance })

        for (i in arr.indices) {
            for (j in arr.indices) {
                if (i != j) queue.offer(Edge(i, j, arr[i][j]))
            }
        }

        var answer = 0L
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (find(cur.src) != find(cur.dest)) {
                union(cur.src, cur.dest)
                answer += cur.distance
            }
        }
        return answer
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

    private data class Edge(
        val src: Int,
        val dest: Int,
        val distance: Int,
    )
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(n) { st.nextToken().toInt() }
    }

    Problem16398().solution(n, arr).also(::println)
}
