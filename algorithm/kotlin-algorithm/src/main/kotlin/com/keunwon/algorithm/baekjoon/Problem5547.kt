package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 일루미네이션
 * Level: 골드-4
 **/
class Problem5547 {
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    private val oddMoves = arrayOf(-1 to 0, 0 to -1, 1 to 0, 1 to 1, 0 to 1, -1 to 1)
    private val evenMoves = arrayOf(-1 to -1, 0 to -1, 1 to -1, 1 to 0, 0 to 1, -1 to 0)
    private var answer = 0

    fun solution(map: Array<IntArray>): Int {
        this.map = Array(map.size) { IntArray(map[0].size + 2) }
        return 0
    }

    private fun bfs() {
        val queue = LinkedList<Node>()
    }

    private data class Node(val x: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (w, h) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(h) {
            val st = StringTokenizer(br.readLine())
            IntArray(w) { st.nextToken().toInt() }
        }

        Problem5547().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}
