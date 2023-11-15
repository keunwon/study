package com.keunwon.algorithm.againresolve

import java.util.*
import kotlin.math.abs

class ALessons81302 {
    fun solution(places: Array<Array<String>>): IntArray = places.map { check(it) }.toIntArray()

    private fun check(place: Array<String>): Int {
        for (i in place.indices) {
            for ((j, c) in place[i].withIndex()) {
                if (c == 'P' && !bfs(Pair(i, j), place)) return 0
            }
        }
        return 1
    }

    private fun bfs(start: Pair<Int, Int>, place: Array<String>): Boolean {
        val queue = LinkedList<Pair<Int, Int>>().apply { offer(start) }
        val (sr, sc) = start

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            for ((mr, mc) in moves) {
                val (nr, nc) = Pair(r + mr, c + mc)

                if (nr !in place.indices || nc !in place[0].indices || start == Pair(nr, nc)) continue

                val manhattan = abs(sr - nr) + abs(sc - nc)
                val element = place[nr][nc]

                if (element == 'P' && manhattan <= 2) return false
                if (element == 'O' && manhattan < 2) queue.offer(Pair(nr, nc))
            }
        }
        return true
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    ALessons81302().solution(
        arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
        )
    ).also { println(it.contentToString()) }
}
