package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 토마토
 * Level:
 **/
class AProblem7576 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Int {
        val queue = LinkedList<Node>().apply {
            startPositions(map).forEach { (r, c) -> offer(Node(r, c)) }
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] != 0) continue

                map[nr][nc] = map[cur.r][cur.c] + 1
                queue.offer(Node(nr, nc))
            }
        }

        var answer = 0
        for (i in map.indices) {
            for (num in map[i]) {
                if (num == 0) return -1
                answer = answer.coerceAtLeast(num)
            }
        }
        return answer - 1
    }

    private fun startPositions(map: Array<IntArray>): List<Node> {
        val startPositions = mutableListOf<Node>()

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == 1) startPositions.add(Node(i, j))
            }
        }
        return startPositions
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(m) {
            val st = StringTokenizer(br.readLine())
            IntArray(n) { st.nextToken().toInt() }
        }

        AProblem7576().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}
