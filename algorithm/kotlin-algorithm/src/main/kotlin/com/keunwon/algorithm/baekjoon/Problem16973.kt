package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 직사각형 탈출
 * Level: 골드-4
 **/
class Problem16973 {
    private lateinit var map: Array<IntArray>
    private lateinit var size: Pair<Int, Int>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(
        map: Array<IntArray>,
        size: Pair<Int, Int>,
        src: Pair<Int, Int>,
        dest: Pair<Int, Int>,
    ): Int {
        this.map = Array(map.size + 1) { IntArray(map[0].size + 1) }
        this.size = size

        for (i in map.indices) {
            for (j in map[0].indices) {
                this.map[i + 1][j + 1] = map[i][j]
            }
        }
        return bfs(
            Node(src.first, src.second, 0),
            Node(dest.first, dest.second, 0),
        )
    }

    private fun bfs(src: Node, dest: Node): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size + 1) { BooleanArray(map[0].size + 1) }
        var answer = Int.MAX_VALUE

        with(src) {
            queue.offer(this)
            visited[r][c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == dest.r && cur.c == dest.c) {
                answer = cur.distance
                break
            }

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in 1 until map.size || nc !in 1 until map[0].size) continue
                if (visited[nr][nc] || !check(nr, nc)) continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc, cur.distance + 1))
            }
        }
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun check(r: Int, c: Int): Boolean {
        val (h, w) = size
        for (i in r until r + h) {
            for (j in c until c + w) {
                if (i !in map.indices || j !in map[0].indices) return false
                if (map[i][j] == 1) return false
            }
        }
        return true
    }

    private data class Node(val r: Int, val c: Int, val distance: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(m) { st.nextToken().toInt() }
        }
        val st = StringTokenizer(br.readLine())
        val positions = { Pair(st.nextToken().toInt(), st.nextToken().toInt()) }

        Problem16973().solution(
            map,
            positions(),
            positions(),
            positions(),
        ).also { bw.write("$it") }
        bw.flush()
    }
}
