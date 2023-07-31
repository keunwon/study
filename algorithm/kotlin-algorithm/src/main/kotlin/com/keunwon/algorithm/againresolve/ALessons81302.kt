package com.keunwon.algorithm.againresolve

import java.util.*
import kotlin.math.abs

class ALessons81302 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(places: Array<Array<String>>): IntArray {
        return places.map { if (check(it)) 1 else 0 }.toIntArray()
    }

    private fun check(place: Array<String>): Boolean {
        for (i in place.indices) {
            for (j in place[0].indices) {
                if (place[i][j] == 'P') {
                    if (!bfs(Position(i, j), place)) return false
                }
            }
        }
        return true
    }

    private fun bfs(start: Position, place: Array<String>): Boolean {
        val queue = LinkedList<Position>().apply { offer(start) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in place.indices || nc !in place[0].indices) continue
                if (start.r == nr && start.c == nc) continue

                val diff = abs(nr - start.r) + abs(nc - start.c)
                val type = place[nr][nc]

                if (diff < 2 && type == 'O') queue.offer(Position(nr, nc))
                else if (diff <= 2 && type == 'P') return false
            }
        }
        return true
    }

    private data class Position(val r: Int, val c: Int)
}

fun main() {
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
    )

    ALessons81302().solution(places).also { println(it.joinToString(", ")) }
}
