package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 움직이는 미로 탈출
 * Level: 골드-3
 **/
class Problem16954 {
    private lateinit var map: Array<CharArray>
    private val moves = arrayOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 0, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    fun solution(map: Array<CharArray>): Int {
        this.map = map
        val queue = LinkedList<Node>().apply { offer(Node(7, 0)) }

        while (queue.isNotEmpty()) {
            val size = queue.size

            for (i in 0 until size) {
                val cur = queue.poll()

                if (map[cur.r][cur.c] == '#') continue

                for ((mr, mc) in moves) {
                    val nr = cur.r + mr
                    val nc = cur.c + mc

                    if (nr !in map.indices || nc !in map[0].indices) continue

                    if (nr == 0 && nc == 7) return 1

                    if (map[nr][nc] != '#') queue.offer(Node(nr, nc))
                }
            }
            down()
        }
        return 0
    }

    private fun down() {
        for (i in 6 downTo 0) {
            for (j in 0 until 8) {
                map[i + 1][j] = map[i][j]
            }
        }

        for (i in 0 until 8) {
            map[0][i] = '.'
        }
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val map = Array(8) { br.readLine().toCharArray() }

        Problem16954().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}
