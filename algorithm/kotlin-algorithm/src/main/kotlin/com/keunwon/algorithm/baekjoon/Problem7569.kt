package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 토마토
 * Level: 골드-5
 **/
class Problem7569 {
    private val moves = arrayOf(
        Triple(0, -1, 0),
        Triple(0, 0, 1),
        Triple(0, 1, 0),
        Triple(0, 0, -1),
        Triple(-1, 0, 0),
        Triple(1, 0, 0),
    )

    fun solution(map: Array<Array<IntArray>>): Int {
        val queue = LinkedList<Node>()

        startPositions(map).forEach { (h, r, c) ->
            queue.offer(Node(h, r, c))
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mh, mr, mc) in moves) {
                val nh = cur.h + mh
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nh !in map.indices || nr !in map[0].indices || nc !in map[0][0].indices) continue
                if (map[nh][nr][nc] != 0) continue

                map[nh][nr][nc] = map[cur.h][cur.r][cur.c] + 1
                queue.offer(Node(nh, nr, nc))
            }
        }

        var answer = 0
        for (h in map.indices) {
            for (r in map[h].indices) {
                for (c in map[h][r].indices) {
                    val num = map[h][r][c]

                    if (num == 0) return -1
                    answer = answer.coerceAtLeast(num)
                }
            }
        }
        return answer - 1
    }

    private fun startPositions(map: Array<Array<IntArray>>): List<Node> {
        val list = mutableListOf<Node>()

        for (h in map.indices) {
            for (r in map[h].indices) {
                for (c in map[h][r].indices) {
                    if (map[h][r][c] == 1) list.add(Node(h, r, c))
                }
            }
        }
        return list
    }

    private data class Node(val h: Int, val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (m, n, h) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(h) {
            Array(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
        }

        Problem7569().solution(map).also { bw.write("$it") }
        bw.newLine()
        bw.flush()
    }
}
