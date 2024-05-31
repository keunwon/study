package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.abs

class Lesson81302 {
    fun solution(places: Array<Array<String>>): IntArray =
        places.map { p -> if (check(p)) 1 else 0 }.toIntArray()

    private fun check(place: Array<String>): Boolean {
        for (i in place.indices) {
            for ((j, type) in place[i].withIndex()) {
                if (type == 'P' && !bfs(Node(i, j), place)) return false
            }
        }
        return true
    }

    private fun bfs(start: Node, place: Array<String>): Boolean {
        val queue = LinkedList<Node>().apply { offer(start) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in place.indices || nc !in place[0].indices) continue
                if (start.r == nr && start.c == nc) continue

                val diff = abs(nr - start.r) + abs(nc - start.c)
                if (diff <= 2 && place[nr][nc] == 'P') return false
                else if (diff < 2 && place[nr][nc] == 'O') queue.offer(Node(nr, nc))
            }
        }
        return true
    }

    private data class Node(val r: Int, val c: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}