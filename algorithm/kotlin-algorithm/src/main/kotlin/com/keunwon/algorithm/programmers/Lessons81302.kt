package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.abs

/**
 * Title: 거리두기 확인하기
 * Level: 2
 **/
class Lessons81302 {
    fun solution(places: Array<Array<String>>): IntArray {
        return places.map { check(it) }.toIntArray()
    }

    private fun check(place: Array<String>): Int {
        for (i in place.indices) {
            for (j in place[0].indices) {
                if (place[i][j] == 'P' && !bfs(Position(i, j), place)) return 0
            }
        }
        return 1
    }

    private fun bfs(start: Position, place: Array<String>): Boolean {
        val queue = LinkedList<Position>().apply { offer(start) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((r, c) in moves) {
                val nr = cur.r + r
                val nc = cur.c + c

                if (nr !in place.indices || nc !in place[0].indices) continue
                if (start == Position(nr, nc)) continue

                val diff = abs(start.r - nr) + abs(start.c - nc)
                if (place[nr][nc] == 'P' && diff <= 2) return false
                if (place[nr][nc] == 'O' && diff < 2) queue.offer(Position(nr, nc))
            }
        }
        return true
    }

    data class Position(val r: Int, val c: Int)

    companion object {
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
